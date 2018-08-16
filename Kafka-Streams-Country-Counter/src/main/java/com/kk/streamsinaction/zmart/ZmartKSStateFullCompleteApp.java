package com.kk.streamsinaction.zmart;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import com.kk.creditcard.model.CorrelatedPurchase;
import com.kk.creditcard.model.Purchase;
import com.kk.creditcard.model.PurchasePattern;
import com.kk.creditcard.model.RewardAccumulator;
import com.kk.streamsinaction.joiner.PurchaseJoiner;
import com.kk.streamsinaction.partitioners.RewardsStreamPartitioner;
import com.kk.streamsinaction.timestamp_extractor.TransactionTimestampExtractor;
import com.kk.streamsinaction.transformers.PurchaseRewardTransformer;
import com.kk.util.JsonDeserializer;
import com.kk.util.JsonSerializer;

public class ZmartKSStateFullCompleteApp {

	public static void main(String[] args) {
		String rewardStateStoreName ="rewardsPointsStore2";
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "zmart-streams-app-id");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, TransactionTimestampExtractor.class);
		
		// Purchase 
		JsonSerializer<Purchase> purchaseSerializer = new JsonSerializer<>();
		JsonDeserializer<Purchase> purchaseDeserializer = new JsonDeserializer<>(Purchase.class);
		
		// Purchase pattern 
		JsonSerializer<PurchasePattern> ppSerializer = new JsonSerializer<>();
		JsonDeserializer<PurchasePattern> ppDeserializer = new JsonDeserializer<>(PurchasePattern.class);
		
		// Coustomer reward 
		JsonSerializer<RewardAccumulator> raSerializer = new JsonSerializer<>();
		JsonDeserializer<RewardAccumulator> raDeserializer = new JsonDeserializer<>(RewardAccumulator.class);
		
		// Correlate purchase 
		JsonSerializer<CorrelatedPurchase> corrPurchaseSerializer = new JsonSerializer<>();
		JsonDeserializer<CorrelatedPurchase> corrPurchaseDeserializer = new JsonDeserializer<>(CorrelatedPurchase.class);
		
		
 		Serde<String> stringSerds = Serdes.String();
		Serde<Purchase> purchaseSerdes = Serdes.serdeFrom(purchaseSerializer, purchaseDeserializer);
		Serde<PurchasePattern> purchasepatternSerdes = Serdes.serdeFrom(ppSerializer, ppDeserializer);
		Serde<RewardAccumulator> raSedes = Serdes.serdeFrom(raSerializer, raDeserializer);
		Serde<CorrelatedPurchase> corrPurchaseSerdes = Serdes.serdeFrom(corrPurchaseSerializer, corrPurchaseDeserializer);
		// State store builder
		StoreBuilder<KeyValueStore<String, Integer>> storeBuilder = Stores.keyValueStoreBuilder(Stores.inMemoryKeyValueStore(rewardStateStoreName), Serdes.String(), Serdes.Integer())
				.withCachingEnabled();
		
		StreamsBuilder builder = new StreamsBuilder();
		// add source ---------------------------------------------------------------------------------------------------------------------------------------------
		KStream<String, Purchase> purchaseStream = builder.stream("purchaseSrc1", Consumed.with(stringSerds, purchaseSerdes));
		// add state store-------------------------------------------------------------------------------------------------------------------------------------------
		builder.addStateStore(storeBuilder);
		// add mask to cc number -----------------------------------------------------------------------------------------------------------------------------------
		purchaseStream = purchaseStream.mapValues((purchase)-> Purchase.builder(purchase).maskCreditCard().build());
		//------------------------------------------- purchase patterns---------------------------------------------------------------------------------------------
		// get Purchase pattern stream 
		KStream<String, PurchasePattern> ppSttream = purchaseStream.mapValues((purchase)-> PurchasePattern.builder(purchase).build());
		// send to purchase pattern data to Kafka topic
		ppSttream.to("patterns", Produced.with(stringSerds, purchasepatternSerdes));
		
		// get Reward accumulator stream
		//------------------------------------Reward program ------------------------------------------------------------------------------------------------------
		RewardsStreamPartitioner rewardsStreamPartitioner = new RewardsStreamPartitioner();
		// Repartition using through
		//KStream<String, Purchase> transByCustomerStream = purchaseStream.through(stringSerds, purchaseSerdes, rewardsStreamPartitioner, "customer_transactions");
		//	KStream<String, Purchase> transByCustomerStream = purchaseStream.through(rewardsStreamPartitioner, "customer_transactions");
		KStream<String, RewardAccumulator> raStream = purchaseStream.transformValues(()->new PurchaseRewardTransformer(rewardStateStoreName), rewardStateStoreName);
		// send rewards to kafka topic
		raStream.to("rewards", Produced.with(stringSerds, raSedes));
		
		
		//----------------------------Branching---------------------------------------------------------------------------------------------------------------------
		
		Predicate<String, Purchase> isCoffee = (key, purchase)-> "coffee".equalsIgnoreCase(purchase.getDepartment());
		Predicate<String, Purchase> isElectronics = (key, purchase)-> "electronics".equalsIgnoreCase(purchase.getDepartment());
		int coffee = 0;
		int electronics =1;
		
		KStream<String, Purchase>[] kstreamByDepart = purchaseStream.selectKey((k,v)->v.getCustomerId()).branch(isCoffee, isElectronics);
		
		//kstreamByDepart[coffee].to("coffee", Produced.with(stringSerds, purchaseSerdes));
		//kstreamByDepart[electronics].to("electronics", Produced.with(stringSerds, purchaseSerdes));
		
		KStream<String, Purchase> coffeeBranch = kstreamByDepart[coffee];
		KStream<String, Purchase> electronicsBrance = kstreamByDepart[electronics];
		
		ValueJoiner<Purchase, Purchase, CorrelatedPurchase> purchaseJoiner = new PurchaseJoiner();
		// four minutes
		JoinWindows twentyMinutesWindow = JoinWindows.of(1000 * 60 * 4);
		KStream<String, CorrelatedPurchase> joinedKstream = coffeeBranch.join(electronicsBrance, purchaseJoiner, twentyMinutesWindow, stringSerds, purchaseSerdes, purchaseSerdes);
		joinedKstream.to("joinedStream", Produced.with(stringSerds, corrPurchaseSerdes));
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// send masked purchase and filtered  to purchase topic
		double minPrice = 5;
		// change key 
		KeyValueMapper<String, Purchase, Long> purchaseKeyMapper = (key, purchase)-> purchase.getPurchaseDate().getTime();
		
		purchaseStream.filter((key, purchase)-> purchase.getPrice() > minPrice).selectKey(purchaseKeyMapper).to("purchase", Produced.with(Serdes.Long(), purchaseSerdes));
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), new StreamsConfig(props));
		kafkaStreams.start();
		System.out.println("Zmart stream app2 started....");

		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

	}

	

}
