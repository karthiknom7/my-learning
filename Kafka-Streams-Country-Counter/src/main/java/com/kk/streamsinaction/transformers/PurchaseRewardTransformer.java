package com.kk.streamsinaction.transformers;

import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

import com.kk.creditcard.model.Purchase;
import com.kk.creditcard.model.RewardAccumulator;

public class PurchaseRewardTransformer implements ValueTransformer<Purchase, RewardAccumulator>{

	private KeyValueStore<String, Integer> stateStore ;
	private final String storeName ;
	private ProcessorContext context;
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	

	public PurchaseRewardTransformer(String storeName) {
		this.storeName = storeName;
	}



	@Override
	public void init(ProcessorContext context) {
		this.context = context;
		stateStore = (KeyValueStore<String, Integer>) this.context.getStateStore(storeName);
	}

	@Override
	public RewardAccumulator punctuate(long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RewardAccumulator transform(Purchase purchase) {
		RewardAccumulator rewardAccumulator = RewardAccumulator.builder(purchase).build();
		Integer accumulatedSoFar = stateStore.get(rewardAccumulator.getCustomerId());
		if(null != accumulatedSoFar){
			rewardAccumulator.addRewardPoints(accumulatedSoFar);
		}
		stateStore.put(rewardAccumulator.getCustomerId(), rewardAccumulator.getTotalRewardPoints());
		return rewardAccumulator;
	}

}
