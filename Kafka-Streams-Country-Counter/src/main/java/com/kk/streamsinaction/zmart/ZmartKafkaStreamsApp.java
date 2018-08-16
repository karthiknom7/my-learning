package com.kk.streamsinaction.zmart;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import com.kk.creditcard.model.Purchase;
import com.kk.creditcard.model.PurchasePattern;
import com.kk.creditcard.model.RewardAccumulator;
import com.kk.util.JsonDeserializer;
import com.kk.util.JsonSerializer;

public class ZmartKafkaStreamsApp {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "zmart-streams-app-id");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		// Purchase 
		JsonSerializer<Purchase> purchaseSerializer = new JsonSerializer<>();
		JsonDeserializer<Purchase> purchaseDeserializer = new JsonDeserializer<>(Purchase.class);
		
		// Purchase pattern 
		JsonSerializer<PurchasePattern> ppSerializer = new JsonSerializer<>();
		JsonDeserializer<PurchasePattern> ppDeserializer = new JsonDeserializer<>(PurchasePattern.class);
		
		// Coustomer reward 
		JsonSerializer<RewardAccumulator> raSerializer = new JsonSerializer<>();
		JsonDeserializer<RewardAccumulator> raDeserializer = new JsonDeserializer<>(RewardAccumulator.class);
		
 		Serde<String> stringSerds = Serdes.String();
		Serde<Purchase> purchaseSerdes = Serdes.serdeFrom(purchaseSerializer, purchaseDeserializer);
		Serde<PurchasePattern> purchasepatternSerdes = Serdes.serdeFrom(ppSerializer, ppDeserializer);
		Serde<RewardAccumulator> raSedes = Serdes.serdeFrom(raSerializer, raDeserializer);
		
		StreamsBuilder builder = new StreamsBuilder();
		// add source
		KStream<String, Purchase> purchaseStream = builder.stream("purchaseSrc", Consumed.with(stringSerds, purchaseSerdes));
		// add mask
		purchaseStream = purchaseStream.mapValues((purchase)-> Purchase.builder(purchase).maskCreditCard().build());
		// get Purchase pattern stream 
		KStream<String, PurchasePattern> ppSttream = purchaseStream.mapValues((purchase)-> PurchasePattern.builder(purchase).build());
		// send to purchase pattern data to Kafka topic
		ppSttream.to("patterns", Produced.with(stringSerds, purchasepatternSerdes));
		// get Reward accumulator stream
		
		KStream<String, RewardAccumulator> raStream = purchaseStream.mapValues((purchase)->RewardAccumulator.builder(purchase).build() );
		// send rewards to kafka topic
		raStream.to("rewards", Produced.with(stringSerds, raSedes));
		// send masked purchase to purchase topic
		purchaseStream.to("purchase", Produced.with(stringSerds, purchaseSerdes));
		
		KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), new StreamsConfig(props));
		kafkaStreams.start();
		System.out.println("Zmart stream app started....");

		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
	}

}
