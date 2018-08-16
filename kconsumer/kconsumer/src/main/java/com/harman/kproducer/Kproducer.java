package com.harman.kproducer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harman.event.EventReader;
import com.harman.util.ZipUtil;

public class Kproducer {
	private static final Logger LOGGER = Logger.getLogger(Kproducer.class);
	private static String host;
	private static String port;
	private static String topic;
	private static int batchSize;
	private static String geFilePath;

	public static void main(String[] args) throws Exception {
		loadProps();
		/*
		 * String data = "hello karthik"; byte[] zipedData =
		 * ZipUtil.zip(data.getBytes()); sendData("key", zipedData, topic);
		 */
		sendGeneralEvents();
	}

	
	private static void loadProps() {
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "producer.properties";
			String workingDir = System.getProperty("user.dir");
			LOGGER.info("workingDirectory**********************->" + workingDir);
			inputStream = new FileInputStream(workingDir + "/" + propFileName);
			prop.load(inputStream);
			host = prop.getProperty("host");
			LOGGER.info("Host : " + host);
			port = prop.getProperty("port");
			LOGGER.info("port : " + port);
			batchSize = Integer.parseInt(prop.getProperty("batch_size"));
			LOGGER.info("batchSize : " + batchSize);
			topic = prop.getProperty("topic");
			LOGGER.info("topic : " + topic);
			geFilePath = prop.getProperty("ge_file_path");
			LOGGER.info("geFilePath : " + geFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sendGeneralEvents() throws Exception {
		EventReader eventReader = new EventReader();
		List<JsonNode> geJsonNodes = eventReader.readGeData(geFilePath);
		ObjectMapper mapper = new ObjectMapper();
		String strJsonNode = null;
		int key = 0;
		for (JsonNode node : geJsonNodes) {
			String pdid = node.get("PDID").textValue();
			strJsonNode = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node.toString());
			byte[] zipedData = ZipUtil.zip(strJsonNode.getBytes());
			publishRecord(topic, pdid, zipedData);
		}
		LOGGER.info("GE events sent successfully");

	}

	public static void publishRecord(String topic, String pdid, byte[] message) {
		try {
			LOGGER.info("inside publishRecord method of KafkaProducerByPartition : kafka topic :" + topic);
			LOGGER.info("setting kafka property config!!");
			Properties producerProps = new Properties();

			producerProps.put("bootstrap.servers", host + ":" + port);
			producerProps.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
			producerProps.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
			producerProps.put("request.timeout.ms", 120000);
			producerProps.put("compression.type", "snappy");
			producerProps.put("batch.size", 0);
			// producerProps.put("linger.ms",100);
			producerProps.put("max.block.ms", 30000);
			producerProps.put("max.in.flight.requests.per.connection", 1);

			KafkaProducer<byte[], byte[]> kafkaProducer = new KafkaProducer<byte[], byte[]>(producerProps);
			ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(topic,
					pdid.getBytes(), message);
			kafkaProducer.send(record, new Callback() {
				public void onCompletion(RecordMetadata metadata, Exception e) {
					if (e != null) {
						LOGGER.error("Exception occurred in KafkaProducerByPartition::onCompletion::Callback");
						e.printStackTrace();
					}
					// logger.info("The offset of the record we just sent is: "
					// + metadata.offset());
				}
			});
			 kafkaProducer.close();
			//LOGGER.info("published to kafka from topic :" + pdid);
		} catch (Exception e) {
			LOGGER.error("Exception occurred in KafkaProducerByPartition::publishRecord");
			e.printStackTrace();
		}

	}
	
	public static void sendData(String key, byte[] zipedData, String topic) {

		// create instance for properties to access producer configs
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", host + ":" + port);

		// Set acknowledgements for producer requests.
		props.put("acks", "all");

		// If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		// Specify buffer size in config
		props.put("batch.size", batchSize);

		// Reduce the no of requests less than 0
		props.put("linger.ms", 1);

		// The buffer.memory controls the total amount of memory available to
		// the producer for buffering.
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		Producer<String, byte[]> producer = new KafkaProducer<String, byte[]>(props);

		producer.send(new ProducerRecord<String, byte[]>(topic, key, zipedData));

		producer.close();
	}


}
