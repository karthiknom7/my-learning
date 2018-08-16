package com.designpatterns.creational.factorymethod;

public class AmazonSNS implements SMSClient {

	private String accessKey;
	private String region;
	
	
	
	public AmazonSNS(String accessKey, String region) {
		super();
		this.accessKey = accessKey;
		this.region = region;
	}



	@Override
	public void sendSMS(String phoneNumber, String text) {

		System.out.println("SMS sent using Amazon SNS sevices");
	}

}
