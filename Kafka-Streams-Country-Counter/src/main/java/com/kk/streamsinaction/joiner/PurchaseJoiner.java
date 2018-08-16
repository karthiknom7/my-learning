package com.kk.streamsinaction.joiner;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.streams.kstream.ValueJoiner;

import com.kk.creditcard.model.CorrelatedPurchase;
import com.kk.creditcard.model.Purchase;

public class PurchaseJoiner implements ValueJoiner<Purchase, Purchase, CorrelatedPurchase>{

	@Override
    public CorrelatedPurchase apply(Purchase purchase, Purchase purchase2) {
        CorrelatedPurchase.Builder builder = CorrelatedPurchase.newBuilder();

        List<String> purchasedItems = Arrays.asList(purchase.getItemPurchased(),
                        purchase2.getItemPurchased());

        builder.withCustomerId(purchase.getCustomerId())
                .withFirstPurchaseDate(purchase.getPurchaseDate())
                .withSecondPurchaseDate(purchase2.getPurchaseDate())
                .withItemsPurchased(purchasedItems)
                .withTotalAmount(purchase.getPrice() + purchase2.getPrice());

        return builder.build();
    }

}
