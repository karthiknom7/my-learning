package com.kk.stock;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

import com.kk.stock.model.StockTickerData;
import com.kk.util.JsonDeserializer;
import com.kk.util.JsonSerializer;

public class StockTickerExample1 {

	public static void main(String[] args) {

		String topicName = "stockTicker";
		
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stock-ticker-app");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		JsonSerializer<StockTickerData> stdSerializer = new JsonSerializer<>();
		JsonDeserializer<StockTickerData> stdDeserializer = new JsonDeserializer<>(StockTickerData.class);
		
		Serde<StockTickerData> stockTickerDataSerdes = Serdes.serdeFrom(stdSerializer, stdDeserializer);
		Serde<String> stringSerds = Serdes.String();
		
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		KTable<String, StockTickerData> stockTickerKtable = streamsBuilder.table(topicName, Consumed.with(stringSerds, stockTickerDataSerdes));
		KStream<String, StockTickerData> stockTickerStream = streamsBuilder.stream(topicName, Consumed.with(stringSerds, stockTickerDataSerdes));
		
		stockTickerKtable.toStream().print("Stocks-Ktable");
		stockTickerStream.print("Stock-KStream");
		
		KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
		
		streams.start();
		System.out.println("Stock ticker stram started.....");
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
		
	}

}
