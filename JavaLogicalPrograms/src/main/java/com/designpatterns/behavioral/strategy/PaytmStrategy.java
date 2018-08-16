package com.designpatterns.behavioral.strategy;

public class PaytmStrategy implements PaymentStrategy {
	
	private String phoneNumber;
	private String password;
	
	

	public PaytmStrategy(String phoneNumber, String password) {
		this.phoneNumber = phoneNumber;
		this.password = password;
	}



	@Override
	public void pay(int amount) {
		// do paytm transaction here
		System.out.println("Payment is done with Paytm");
	}

}
