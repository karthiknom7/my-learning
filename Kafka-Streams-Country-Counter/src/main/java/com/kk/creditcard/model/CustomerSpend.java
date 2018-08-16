package com.kk.creditcard.model;

public class CustomerSpend {
	private String customerId;
	private double totalSpent;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(double totalSpent) {
		this.totalSpent = totalSpent;
	}

	public static CustomerSpend buildCustomerSpend(Purchase purchase){
		CustomerSpend spend = new CustomerSpend();
		spend.setCustomerId(purchase.getCustomerId());
		spend.setTotalSpent(purchase.getPrice());
		return spend;
	}
	
	public static CustomerSpend reduce(CustomerSpend v1, CustomerSpend v2){
		v1.setTotalSpent(v1.getTotalSpent() + v2.getTotalSpent());
		return v1;
	}
}
