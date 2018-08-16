package com.spark.streaming.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import scala.Tuple2;

public class KafkaTest {

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("kafka-test");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		 JavaStreamingContext streamingContext = new JavaStreamingContext(sparkContext, Durations.seconds(30)); 
		 
		Map<String, Object> kafkaParams = new HashMap<>(); 
        kafkaParams.put("bootstrap.servers", "localhost:9092"); 
        kafkaParams.put("key.deserializer", StringDeserializer.class); 
        kafkaParams.put("value.deserializer", StringDeserializer.class); 
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_strea"); 
        kafkaParams.put("auto.offset.reset", "earliest"); 
        
        List<String> topics = Arrays.asList("sparkTest");
        
        final JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaUtils.createDirectStream(streamingContext,LocationStrategies.PreferConsistent(), 
                ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)); 
        
        JavaDStream<String> words = stream.map(record-> record.value()); 
       JavaDStream<String> allWords = words.flatMap(str-> Arrays.asList(str.split(" ")).iterator());
       JavaPairDStream<String, Integer> allPairs = allWords.mapToPair(str -> new Tuple2<>(str, 1));
      JavaPairDStream<String, Integer> countsPair =  allPairs.reduceByKey((v1, v2) -> v1+v2);
      countsPair.print();
      
       streamingContext.start();
       try {
		streamingContext.awaitTermination();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
