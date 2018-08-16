package nl.amis.streams.sedezation;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDeserializer implements Deserializer<User>{

	public UserDeserializer() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User deserialize(String arg0, byte[] arg1) {

		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = mapper.readValue(arg1, User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
