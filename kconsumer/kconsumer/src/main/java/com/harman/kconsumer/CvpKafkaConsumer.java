package com.harman.kconsumer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

public class CvpKafkaConsumer {

	private static final Logger LOGGER = Logger.getLogger(CvpKafkaConsumer.class);
	private  String hosts;
	private  String topics;
	//private  String group;
	private String keyDesrializer;
	private String valueDeserializer;

	public void startKafkaConsumer() {
		loadProps();
		Properties props = new Properties();
		props.put("bootstrap.servers", hosts);
		//props.put("group.id", group);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", keyDesrializer);
		props.put("value.deserializer", valueDeserializer);
		KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<byte[], byte[]>(props);

		consumer.subscribe(getTopicsAsList(topics));
		LOGGER.info("Subscribed to topic " + topics);

		while (true) {
			ConsumerRecords<byte[], byte[]> records = consumer.poll(100);
			for (ConsumerRecord<byte[], byte[]> record : records) {
				LOGGER.info("Topic is: " + record.topic());
				/*LOGGER.info("After unzip >>>>> offset = " + record.offset() + ", key = " + new String(record.key()) + " , value = "
						+ ZipUtil.unzipData(record.value()));*/
				
				//TODO received record(notification from API) can be used to update settings 
			}
		}
	}

	private void loadProps() {
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "consumer.properties";
			String workingDir = System.getProperty("user.dir");
			LOGGER.info("workingDirectory : " + workingDir);
			inputStream = new FileInputStream(workingDir + "/" + propFileName);
			prop.load(inputStream);
			hosts = prop.getProperty("kafka_hosts");
			LOGGER.info("Host : " + hosts);
			topics = prop.getProperty("kafka_topics");
			LOGGER.info("topics : " + topics);
			/*group = prop.getProperty("group");
			LOGGER.info("group : " + group);*/
			keyDesrializer = prop.getProperty("kafka_key_serializer");
			valueDeserializer = prop.getProperty("kafka_value_serializer");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<String> getTopicsAsList(String topics) {
		List<String> topicsList = null;
		if (topics != null && topics.contains(",")) {
			topicsList = Arrays.asList(topics.split(","));
		} else {
			topicsList = new ArrayList<String>();
			topicsList.add(topics);
		}
		return topicsList;
	}
}
