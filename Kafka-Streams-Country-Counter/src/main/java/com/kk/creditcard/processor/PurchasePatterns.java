package com.kk.creditcard.processor;

import org.apache.kafka.streams.processor.AbstractProcessor;

import com.kk.creditcard.model.Purchase;
import com.kk.creditcard.model.PurchasePattern;

public class PurchasePatterns extends AbstractProcessor<String, Purchase> {

	@Override
	public void process(String key, Purchase purchase) {
		PurchasePattern purchasePattern = PurchasePattern.builder().date(purchase.getPurchaseDate())
				.item(purchase.getItemPurchased()).zipCode(purchase.getZipCode()).build();
		context().forward(key, purchasePattern);
		context().commit();
	}

}
