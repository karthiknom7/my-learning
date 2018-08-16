package nl.amis.streams.sedezation;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class UserSerdes implements Serde<User>{

	private UserDeserializer deserializer = new UserDeserializer();
	private UserSerializer serializer = new UserSerializer();
	
	
	@Override
	public void close() {
		serializer.close();
		deserializer.close();
		
	}

	@Override
	public void configure(Map<String, ?> configmap, boolean isKey) {
		serializer.configure(configmap, isKey);
		deserializer.configure(configmap, isKey);
		
	}

	@Override
	public Deserializer<User> deserializer() {
		return deserializer;
	}

	@Override
	public Serializer<User> serializer() {
		return serializer;
	}

}
