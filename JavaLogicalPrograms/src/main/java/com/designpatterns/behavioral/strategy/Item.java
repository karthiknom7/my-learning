package com.designpatterns.behavioral.strategy;

public class Item {

	private int price;
	private String upcCode;
	
	public Item(int price, String upcCode) {
		super();
		this.price = price;
		this.upcCode = upcCode;
	}

	public int getPrice() {
		return price;
	}

	public String getUpcCode() {
		return upcCode;
	}
	
	
}
