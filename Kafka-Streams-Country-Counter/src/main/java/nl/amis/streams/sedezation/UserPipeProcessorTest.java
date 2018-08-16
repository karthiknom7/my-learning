package nl.amis.streams.sedezation;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

public class UserPipeProcessorTest {

	public static void main(String[] args) {
		
	//	UserSerializer userSerializer = new UserSerializer();
		//UserDeserializer userDeserializer = new UserDeserializer();
				
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "user-pipe-serdes-test");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, UserSerdes.class);
		
		Topology topology = new Topology();
		topology.addSource("Source", "UserIn");
		topology.addProcessor("Processor", ()-> new UserPipeProcessor(), "Source");
		topology.addSink("Sink", "UserOut", "Processor");
		
		KafkaStreams streams = new KafkaStreams(topology, props);
		streams.start();
		System.out.println("user pipe stream started.....");
		
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			streams.close();
		}));
	}

}
