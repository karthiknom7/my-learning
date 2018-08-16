package nl.amis.streams.word;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

public class LineSplit {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-split-word");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		
		StreamsBuilder builder = new StreamsBuilder();
		
		KStream<String, String> source = builder.stream("Test_WC");
		KStream<String, String> words = source.flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")));
		words.to("WordsWithCountsTopic");
		
		Topology topology = builder.build();
		
		KafkaStreams streams = new KafkaStreams(topology, props);
		streams.start();
		
		System.out.println("Split stream started...");
		
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			streams.close();
		}));
	}

}
