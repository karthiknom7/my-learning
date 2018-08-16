package com.designpatterns.creational.factorymethod;

public class TwilioSMSClient implements SMSClient {

	private String url;
	
	public TwilioSMSClient(String url) {
		super();
		this.url = url;
	}

	@Override
	public void sendSMS(String phoneNumber, String text) {

		System.out.println("SMS sent usig Twilio services....");
	}

}
