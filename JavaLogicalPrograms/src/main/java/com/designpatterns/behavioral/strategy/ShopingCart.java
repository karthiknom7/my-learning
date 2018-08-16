package com.designpatterns.behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

public class ShopingCart {

	private List<Item> items;
	
	public ShopingCart(){
		items = new ArrayList<>();
	}
	
	public void addItem(Item item){
		items.add(item);
	}
	
	public void removeItem(Item item){
		items.remove(item);
	}
	
	public int calculateTotal(){
		int total = 0 ;
		for(Item item: items){
			total += item.getPrice();
		}
		return total;
	}
	
	public void pay(PaymentStrategy paymentStrategy){
		int total = calculateTotal();
		paymentStrategy.pay(total);
	}
}
