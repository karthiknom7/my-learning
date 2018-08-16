package com.designpatterns.behavioral.strategy;

public class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private String cvv;
	private String cardNum;
	private String dateOfExpiry;
	
	
	public CreditCardStrategy(String name, String cvv, String cardNum, String dateOfExpiry) {
		this.name = name;
		this.cvv = cvv;
		this.cardNum = cardNum;
		this.dateOfExpiry = dateOfExpiry;
	}


	@Override
	public void pay(int amount) {
		//do credit card payment calculation 
		System.out.println("Pyment done with credit card");
		
	}

}
