package com.kk.stock;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import com.kk.stock.model.StockTransaction;
import com.kk.stock.model.StockTransactionSummary;
import com.kk.util.JsonDeserializer;
import com.kk.util.JsonSerializer;

public class StockSummaryStatefulProcessorDriver {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stock-summery-processor-app");

		// KeyValueStore<String, StockTransactionSummary> summeryStore =
		// KeyValueStore
		JsonDeserializer<StockTransactionSummary> stockTsummeryDeserializer = new JsonDeserializer<>(
				StockTransactionSummary.class);
		JsonSerializer<StockTransactionSummary> stockTsummerySerializer = new JsonSerializer<>();
		
		JsonDeserializer<StockTransaction> stockTranxDeserializer = new JsonDeserializer<>(StockTransaction.class);
		JsonSerializer<StockTransaction> stocktranxSerializer = new JsonSerializer<>();
		
		StringSerializer stringSerializer = new StringSerializer();
        StringDeserializer stringDeserializer = new StringDeserializer();

		/*Map<String, String> changelogConfig = new HashMap<>();
		changelogConfig.put("min.insyc.replicas", "1");*/

		StoreBuilder<KeyValueStore<String, StockTransactionSummary>> storeBuilder = Stores
				.keyValueStoreBuilder(Stores.persistentKeyValueStore("summeryStore"), Serdes.String(),
						Serdes.serdeFrom(stockTsummerySerializer, stockTsummeryDeserializer))
				.withLoggingDisabled();
		
		Topology topology = new Topology();
		topology.addSource("STOCK-SOURCE", stringDeserializer, stockTranxDeserializer, "src-stocks");
		topology.addProcessor("STOCK-PROCESSOR", StockSummaryProcessor::new, "STOCK-SOURCE");
		topology.addStateStore(storeBuilder, "STOCK-PROCESSOR");
		topology.addSink("STOCK-SINK", "stock-outs", stringSerializer, stocktranxSerializer, "STOCK-SOURCE");
		topology.addSink("STOCK-SINK1", "stock-summery",stringSerializer, stockTsummerySerializer, "STOCK-PROCESSOR");
		
		
		KafkaStreams streams = new KafkaStreams(topology, props);
		streams.start();
		System.out.println("Stocks stream started....");
		
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			streams.close();
		}));
	}
}
