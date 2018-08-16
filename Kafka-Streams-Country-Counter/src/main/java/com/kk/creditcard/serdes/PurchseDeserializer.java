package com.kk.creditcard.serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.creditcard.model.Purchase;

public class PurchseDeserializer implements Deserializer<Purchase>{

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Purchase deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		Purchase purchase = null;
		try {
			purchase = mapper.readValue(arg1, Purchase.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchase;
	}

}
