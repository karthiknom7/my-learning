package com.kk.creditcard.serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.creditcard.model.Purchase;

public class PurchaseSerializer implements Serializer<Purchase>{

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String key, Purchase purchse) {
		byte[] retVau = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			retVau = mapper.writeValueAsString(purchse).getBytes();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVau;
	}

}
