package com.designpatterns.behavioral.strategy;

/**
 * 
 * Strategy design pattern is one of the behavioral design pattern. Strategy
 * pattern is used when we have multiple algorithm for a specific task and
 * client decides the actual implementation to be used at runtime
 * 
 * @author KNarayanaswa
 *
 */
public class StrategyPatternTest {

	public static void main(String[] args) {

		ShopingCart shopingCart = new ShopingCart();
		shopingCart.addItem(new Item(200, "djsdsdj"));
		shopingCart.addItem(new Item(300, "abcde"));
		// pay using paytm
		shopingCart.pay(new PaytmStrategy("9008359887", "lala@atym"));
		// or pay using credit card 
		shopingCart.pay(new CreditCardStrategy("karthik", "123", "3245-9999-6543-9765", "06-25"));
		System.out.println("Done");
	}

}
