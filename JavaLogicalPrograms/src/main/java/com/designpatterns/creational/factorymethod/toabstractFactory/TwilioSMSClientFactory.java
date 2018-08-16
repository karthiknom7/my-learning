package com.designpatterns.creational.factorymethod.toabstractFactory;

import com.designpatterns.creational.factorymethod.SMSClient;
import com.designpatterns.creational.factorymethod.TwilioSMSClient;

public class TwilioSMSClientFactory implements SMSClientAbstractFactory{

	private String url;

	public TwilioSMSClientFactory(String url) {
		super();
		this.url = url;
	}

	@Override
	public SMSClient creatSMSClient() {
		return new TwilioSMSClient(url);
	}
	
	
	
}
