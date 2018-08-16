package com.designpatterns.creational.factorymethod.toabstractFactory;

import com.designpatterns.creational.factorymethod.AmazonSNS;
import com.designpatterns.creational.factorymethod.SMSClient;

public class AmazonSMSFactory implements SMSClientAbstractFactory{

	private String accessKey;
	private String region;
	public AmazonSMSFactory(String accessKey, String region) {
		super();
		this.accessKey = accessKey;
		this.region = region;
	}
	@Override
	public SMSClient creatSMSClient() {
		return new AmazonSNS(accessKey, region);
	}
	
	
	
}
