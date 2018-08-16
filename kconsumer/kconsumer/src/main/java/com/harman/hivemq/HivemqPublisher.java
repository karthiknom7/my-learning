package com.harman.hivemq;

import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class HivemqPublisher {
	
	public static final String BROKER_URL = "tcp://localhost:1883";
	private MqttClient client;
	public static final String TOPIC_BRIGHTNESS = "home/brightness";
    public static final String TOPIC_TEMPERATURE = "home/temperature";
	
	public HivemqPublisher(){
		String clientId = "mypublisher";
		try {
			client = new MqttClient(BROKER_URL, clientId);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start(){
		try {
           MqttConnectOptions options = new MqttConnectOptions();
           options.setCleanSession(false);
           options.setWill(client.getTopic("home/LWT"), "I'm gone :(".getBytes(), 0, false);

           client.connect(options);

           //Publish data forever
           while (true) {

               publishBrightness();

               Thread.sleep(500);

               publishTemperature();

               Thread.sleep(500);
           }
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	private void publishTemperature() throws MqttException {
        final MqttTopic temperatureTopic = client.getTopic(TOPIC_TEMPERATURE);

        final int temperatureNumber = getRandon(20, 30);
        final String temperature = temperatureNumber + "°C";

        temperatureTopic.publish(new MqttMessage(temperature.getBytes()));

        System.out.println("Published data. Topic: " + temperatureTopic.getName() + "  Message: " + temperature);
    }

    private void publishBrightness() throws MqttException {
        final MqttTopic brightnessTopic = client.getTopic(TOPIC_BRIGHTNESS);

        final int brightnessNumber =getRandon(0, 100);
        final String brigthness = brightnessNumber + "%";

        brightnessTopic.publish(new MqttMessage(brigthness.getBytes()));

        System.out.println("Published data. Topic: " + brightnessTopic.getName() + "   Message: " + brigthness);
    }

    public static void main(String... args) {
        final HivemqPublisher publisher = new HivemqPublisher();
        publisher.start();
    }
    
    public static int getRandon(int low, int high){
    	Random r = new Random();
    	return r.nextInt(high-low) + low;
    }

}
