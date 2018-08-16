package com.kk.creditcard.processor;

import org.apache.kafka.streams.processor.AbstractProcessor;

import com.kk.creditcard.model.Purchase;
import com.kk.creditcard.model.RewardAccumulator;

public class CustomerRewards extends AbstractProcessor<String, Purchase>{

	@Override
	public void process(String key, Purchase purchase) {
		String customer = purchase.getFirstName() + " " + purchase.getLastName();
		double amount = purchase.getPrice() * purchase.getQuantity();
		RewardAccumulator accumulator = new RewardAccumulator(customer, amount);
		context().forward(key, accumulator);
		context().commit();
	}

}
