package nl.amis.streams.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.StateStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import nl.amis.streams.utils.AppConstants;

public class AppendProcessorTest {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "append-processor");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		Map<String, String> changelogConfig = new HashMap<>();
		changelogConfig.put("min.insyc.replicas", "1");

		StoreBuilder<KeyValueStore<String, String>> stateStore = Stores
				.keyValueStoreBuilder(Stores.persistentKeyValueStore("append-store"), Serdes.String(), Serdes.String())
				.withLoggingEnabled(changelogConfig);

		Topology topology = new Topology();

		topology.addSource("Source", AppConstants.IN_TOPIC);
		topology.addProcessor("Processor", AppendProcessor::new, "Source");
		topology.addStateStore(stateStore, "Processor");
		topology.addSink("Sink", AppConstants.OUT_TOPIC, "Processor");
		System.out.println(topology.describe());

		KafkaStreams streams = new KafkaStreams(topology, props);
		streams.start();
		System.out.println("Append stream started...");

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			streams.close();
		}));
	}

}
