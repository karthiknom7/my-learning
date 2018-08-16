package nl.amis.streams.examples;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import nl.amis.streams.utils.AppConstants;

public class PipeProcessorTest {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "pipe-processor");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		Topology topology = new Topology();
		
		topology.addSource("Source", AppConstants.IN_TOPIC);
		topology.addProcessor("Processor", PipeProcessor::new, "Source");
		topology.addSink("Sink", AppConstants.OUT_TOPIC, "Processor");
		
		KafkaStreams kafkaStreams = new KafkaStreams(topology, props);
		kafkaStreams.setUncaughtExceptionHandler((thread, throable)->{
			System.out.println("Exception occurred");
			System.out.println(thread.getName());
			throable.printStackTrace();
		});
		kafkaStreams.start();
		System.out.println("Started.....");
		
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			kafkaStreams.close();
		}));
	}

}
