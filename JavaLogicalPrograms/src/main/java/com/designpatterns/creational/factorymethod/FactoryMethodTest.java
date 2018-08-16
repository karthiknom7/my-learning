package com.designpatterns.creational.factorymethod;

import com.designpatterns.creational.factorymethod.toabstractFactory.AmazonSMSFactory;
import com.designpatterns.creational.factorymethod.toabstractFactory.TwilioSMSClientFactory;

/**
 * Factory design pattern is used when we have a super class with multiple
 * sub-classes and based on input, we need to return one of the sub-class. This
 * pattern take out the responsibility of instantiation of a class from client
 * program to the factory class.
 * 
 * @author KNarayanaswa
 *
 */
public class FactoryMethodTest {

	public static void main(String[] args) {

		
		//***************** Factory method *******************//
		SMSClient client = SMSClientFactory.getSMSClient("Amazon");
		client.sendSMS("8988787778", "hello");
		
		client = SMSClientFactory.getSMSClient("Twilio");
		client.sendSMS("8988787778", "hello");
		
		//***************** Abstract factory method *****************//
		SMSClient amazonSNS = SMSClientFactory.getSMSClient(new AmazonSMSFactory("my access key", "my region"));
		amazonSNS.sendSMS("8988787778", "hello");
		
		SMSClient twilio = SMSClientFactory.getSMSClient(new TwilioSMSClientFactory("twilio url"));
		twilio.sendSMS("8988787778", "hello");
		
	}

}
