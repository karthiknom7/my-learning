package com.kk.creditcard.processor;

import org.apache.kafka.streams.processor.AbstractProcessor;

import com.kk.creditcard.model.Purchase;

public class CreditcardAnonymizer extends AbstractProcessor<String, Purchase>{

	private static final String CC_NUMBER_REPLACEMENT="xxxx-xxxx-xxxx-";
	@Override
	public void process(String key, Purchase purchase) {
		String last4Digits = purchase.getCreditCardNumber().split("-")[3];
		Purchase updated = Purchase.builder(purchase).creditCardNumber(CC_NUMBER_REPLACEMENT+last4Digits).build();
		System.out.println(" In CreditcardAnonymizer Key : " + key + " Val " + updated );
		context().forward(key, updated);
		context().commit();
	}

}
