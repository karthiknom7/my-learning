package com.kk.streamsinaction.zmart;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter;
import com.kk.creditcard.model.CustomerSpend;
import com.kk.creditcard.model.Purchase;
import com.kk.util.JsonDeserializer;
import com.kk.util.JsonSerializer;

public class ZmartFindTotalSpentByCustomerKtabl {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "zmart-customer-total-spent-streams-app-id");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		
		// Purchase 
		JsonSerializer<Purchase> purchaseSerializer = new JsonSerializer<>();
		JsonDeserializer<Purchase> purchaseDeserializer = new JsonDeserializer<>(Purchase.class);
		
		JsonSerializer<CustomerSpend> customerSpendSerializer = new JsonSerializer<>();
		JsonDeserializer<CustomerSpend> customerSpendDeserializer = new JsonDeserializer<>(CustomerSpend.class);
		// serdes
		Serde<String> stringSerds = Serdes.String();
		Serde<Purchase> purchaseSerdes = Serdes.serdeFrom(purchaseSerializer, purchaseDeserializer);
		Serde<CustomerSpend> csSerdes = Serdes.serdeFrom(customerSpendSerializer, customerSpendDeserializer);
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		KStream<String, Purchase> purchaseStream = streamsBuilder.stream("purchaseSrc1", Consumed.with(stringSerds, purchaseSerdes));
		KStream<String, CustomerSpend> csStream = purchaseStream.mapValues(CustomerSpend::buildCustomerSpend);
		KGroupedStream<String, CustomerSpend> kgrpStream = csStream.groupBy((key, value)-> value.getCustomerId(),stringSerds, csSerdes);
		KTable<String, CustomerSpend> csKtable = kgrpStream.reduce(CustomerSpend::reduce, "customerSpent");
		csKtable.toStream().to("customerSpend", Produced.with(stringSerds, csSerdes));
		
		KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
		
		streams.start();
		System.out.println("Customer Spend stream started.........");
		
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}

}
