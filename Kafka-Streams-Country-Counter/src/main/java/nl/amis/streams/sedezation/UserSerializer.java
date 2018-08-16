package nl.amis.streams.sedezation;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserSerializer implements Serializer<User>{

	public UserSerializer() {
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
	public byte[] serialize(String arg0, User user) {
		byte[] retVau = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			retVau = mapper.writeValueAsString(user).getBytes();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVau;
	}
	

}
