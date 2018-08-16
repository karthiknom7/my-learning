package com.kk.streamsinaction.timestamp_extractor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import com.kk.creditcard.model.Purchase;

public class TransactionTimestampExtractor implements TimestampExtractor{

	@Override
    public long extract(ConsumerRecord<Object, Object> record, long previousTimestamp) {
        Purchase purchaseTransaction = (Purchase) record.value();
        return purchaseTransaction.getPurchaseDate().getTime();
    }

}
