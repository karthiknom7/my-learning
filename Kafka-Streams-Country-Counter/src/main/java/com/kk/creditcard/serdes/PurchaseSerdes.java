package com.kk.creditcard.serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.kk.creditcard.model.Purchase;

public class PurchaseSerdes implements Serde<Purchase>{

	private PurchaseSerializer serializer = new PurchaseSerializer();
	private PurchseDeserializer deserializer = new PurchseDeserializer();
	
	@Override
	public void close() {
		serializer.close();
		deserializer.close();
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {

		serializer.configure(arg0, arg1);
		deserializer.configure(arg0, arg1);
	}

	@Override
	public Deserializer<Purchase> deserializer() {
		return deserializer;
	}

	@Override
	public Serializer<Purchase> serializer() {
		return serializer;
	}

}
