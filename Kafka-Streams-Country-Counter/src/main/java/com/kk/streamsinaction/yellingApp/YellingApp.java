package com.kk.streamsinaction.yellingApp;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class YellingApp {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "yelling-app-id");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		Serde<String> stringSerdes = Serdes.String();
		
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		KStream<String, String> simpleFirstStream = streamsBuilder.stream( "src-topic", Consumed.with(stringSerdes, stringSerdes));
		KStream<String, String> mapperStream = simpleFirstStream.mapValues(String::toUpperCase);
		mapperStream.to("out-topic", Produced.with(stringSerdes, stringSerdes));
		
		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), new StreamsConfig(props));
		kafkaStreams.start();
		System.out.println("Yelling Streams App started....");
		
		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
	}

}
