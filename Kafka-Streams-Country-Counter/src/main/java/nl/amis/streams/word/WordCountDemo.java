package nl.amis.streams.word;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

/**
 * Demonstrates, using the high-level KStream DSL, how to implement the WordCount program
 * that computes a simple word occurrence histogram from an input text.
 *
 * In this example, the input stream reads from a topic named "streams-file-input", where the values of messages
 * represent lines of text; and the histogram output is written to topic "streams-wordcount-output" where each record
 * is an updated count of a single word.
 *
 * Before running this example you must create the source topic (e.g. via bin/kafka-topics.sh --create ...)
 * and write some data to it (e.g. via bin-kafka-console-producer.sh). Otherwise you won't see any data arriving in the output topic.
 */
public class WordCountDemo {

	public static void main(final String[] args) throws Exception {
	    Properties config = new Properties();
	    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
	    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	   // config.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
	    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
	    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

	    /*KStreamBuilder builder = new KStreamBuilder();
	    KStream<String, String> textLines = builder.stream("Test_WC");
	    KTable<String, Long> wordCounts = textLines
	        .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
	        .groupBy((key, word) -> word)
	        .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
	    wordCounts.to(Serdes.String(), Serdes.Long(), "WordsWithCountsTopic");

	    KafkaStreams streams = new KafkaStreams(builder, config);
	    streams.start();
	    System.out.println("Started....");

	    Runtime.getRuntime().addShutdownHook(new Thread(streams::close));*/
	    
	    StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream("Test_WC");
        KTable<String, Long> wordCounts = textLines
            .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
            .groupBy((key, word) -> word)
            .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
        wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));
 
        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
			streams.close();
		}));
	  }
}

 