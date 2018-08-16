package io.github.timothyrenner.kstreamex.tablejoin;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Properties;
import java.util.Random;

/** Demonstrates a KTable-KTable join.
 *
 * @author Timothy Renner
 */
public class TableJoinKafkaStream {

    /** Runs the streams program, writing to the "long-avgs" topic.
     *
     * @param args Not used.
     */ 
    public static void main(String[] args) throws Exception {
        
        Properties config = new Properties();

        config.put(StreamsConfig.APPLICATION_ID_CONFIG,
            "table-join-kafka-streams");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
            "localhost:9092");
        config.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG,
            "localhost:2181");
        config.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG,
            Serdes.String().getClass().getName());
        config.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG,
            Serdes.Long().getClass().getName());
        
        KStreamBuilder builder = new KStreamBuilder();

        KStream<String, Long> longs = builder.stream(
            Serdes.String(), Serdes.Long(), "longs");

        // In one ktable, count by key on a ten second tumbling window.
        KTable<Windowed<String>, Long> longCounts = 
            longs.groupByKey(Serdes.String(), Serdes.Long())
                 .count(TimeWindows.of("longCounts", 10000L)
                                   .until(10000L));

        // In another ktable, sum the values on a ten second tumbling window.
        KTable<Windowed<String>, Long> longSums = 
            longs.groupByKey(Serdes.String(), Serdes.Long())
                 .reduce((v1, v2) -> v1 + v2,
                         TimeWindows.of("longSums", 10000L)
                                    .until(10000L));

        // We can join the two tables to get the average.
        KTable<Windowed<String>, Double> longAvgs = 
            longSums.join(longCounts,
                          (sum, count) -> 
                            sum.doubleValue()/count.doubleValue());

        // Finally, sink to the long-avgs topic.
        longAvgs.toStream((wk, v) -> wk.key())
                // Note that we need to filter null values, which
                // can be emitted when the window state store is empty.
                .filter((k,v) -> v != null)
                .to(Serdes.String(),
                    Serdes.Double(),
                    "long-avgs");

        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();
        
        // Now generate the data and write to the topic.
        Properties producerConfig = new Properties();
        producerConfig.put("bootstrap.servers", "localhost:9092");
        producerConfig.put("key.serializer",
                           "org.apache.kafka.common" + 
                           ".serialization.StringSerializer");
        producerConfig.put("value.serializer",
                           "org.apache.kafka.common" +
                           ".serialization.LongSerializer");

        KafkaProducer producer = 
            new KafkaProducer<String, Long>(producerConfig);

        Random rng = new Random(123456L);
        
        // These will be the keys used in the stream.
        String[] keys = {"A", "B", "C"};

        while(true) {
            
            // Select one of the keys at random.
            String key = keys[rng.nextInt(keys.length)];
    
            // Generate a long between 0 and 10 and send to Kafka.
            producer.send(new ProducerRecord<>(
                "longs", key, rng.nextLong()%10));
            
            Thread.sleep(100L);
        } // Close infinite data generating loop.
    } // Close main.
} // Close TableJoinKafkaStream.
