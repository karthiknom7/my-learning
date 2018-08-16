package com.kk.creditcard;

import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import com.kk.creditcard.model.Purchase;
import com.kk.creditcard.model.PurchasePattern;
import com.kk.creditcard.model.RewardAccumulator;
import com.kk.creditcard.processor.CreditcardAnonymizer;
import com.kk.creditcard.processor.CustomerRewards;
import com.kk.creditcard.processor.PurchasePatterns;
import com.kk.util.JsonDeserializer;
import com.kk.util.JsonSerializer;

public class PurchaseProcessorDriver {

	public static void main(String[] args) {

		JsonDeserializer<Purchase> purchaseJsonDeserializer = new JsonDeserializer<>(Purchase.class);
		JsonSerializer<Purchase> purchaseJsonSerializer = new JsonSerializer<>();

		JsonDeserializer<PurchasePattern> purchasePatternJsonDeserializer = new JsonDeserializer<>(
				PurchasePattern.class);
		JsonSerializer<PurchasePattern> purchasePatternSerializer = new JsonSerializer<>();
		
		JsonDeserializer<RewardAccumulator> rAJsonDeserializer = new JsonDeserializer<>(
				RewardAccumulator.class);
		JsonSerializer<RewardAccumulator> rASerializer = new JsonSerializer<>();

		Properties props = new Properties();
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "credit-card-processing-app");
		// props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
		// Serdes.String());
		StringDeserializer stringDeserializer = new StringDeserializer();
		StringSerializer stringSerializer = new StringSerializer();

		Topology topology = new Topology();
		topology.addSource("SOURCE", stringDeserializer, purchaseJsonDeserializer, "purchaseSource");
		topology.addProcessor("PROCESSOR1", CreditcardAnonymizer::new, "SOURCE");
		topology.addProcessor("PROCESSOR2", CustomerRewards::new, "PROCESSOR1");
		topology.addProcessor("PROCESSOR3", PurchasePatterns::new, "PROCESSOR1");
		topology.addSink("SINK1", "purchase", stringSerializer, purchaseJsonSerializer, "PROCESSOR1");
		topology.addSink("SINK2", "rewards",stringSerializer,rASerializer, "PROCESSOR2");
		topology.addSink("SINK3", "patterns",stringSerializer,purchasePatternSerializer, "PROCESSOR3");

		KafkaStreams streams = new KafkaStreams(topology, props);
		streams.start();
		System.out.println("PurchaseProcessorDriver server...");

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			streams.close();
		}));

	}

}
