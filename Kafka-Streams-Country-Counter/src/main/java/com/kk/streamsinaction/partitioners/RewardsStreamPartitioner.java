package com.kk.streamsinaction.partitioners;

import org.apache.kafka.streams.processor.StreamPartitioner;

import com.kk.creditcard.model.Purchase;

public class RewardsStreamPartitioner implements StreamPartitioner<String, Purchase>{

	@Override
	public Integer partition(String key, Purchase value, int partitions) {
		return value.getCustomerId().hashCode() % partitions;
	}

}
