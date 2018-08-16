package com.kk.util;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer<T> implements Deserializer<T> {

	//private Gson gson = new Gson();
	private Class<T> deserializedClass;

	public JsonDeserializer(Class<T> deserializedClass) {
		this.deserializedClass = deserializedClass;
	}

	public JsonDeserializer() {
	}

	@Override
	@SuppressWarnings("unchecked")
	public void configure(Map<String, ?> map, boolean b) {
		if (deserializedClass == null) {
			deserializedClass = (Class<T>) map.get("serializedClass");
		}
	}

	@Override
	public T deserialize(String s, byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		// return gson.fromJson(new String(bytes),deserializedClass);

		ObjectMapper mapper = new ObjectMapper();
		T t = null;
		try {
			t = mapper.readValue(bytes, deserializedClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;

	}

	@Override
	public void close() {

	}
}
