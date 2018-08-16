package com.designpatterns.creational.factorymethod;

import com.designpatterns.creational.factorymethod.toabstractFactory.SMSClientAbstractFactory;

public class SMSClientFactory {

	public static SMSClient getSMSClient(String type){
		SMSClient client = null;
		if(type.equals("Amazon")){
			client = new AmazonSNS("accessKey", "myRigion");
		}else if(type.equals("Twilio")){
			client = new TwilioSMSClient("twilio url");
		}
		return client;
	}
	
	public static SMSClient getSMSClient(SMSClientAbstractFactory smsClientAbstractFactory) {
		return smsClientAbstractFactory.creatSMSClient();
	}
}
