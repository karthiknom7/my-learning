package com.harman.event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EventReader {

	private static final Logger LOGGER = Logger.getLogger(EventReader.class);

	public List<JsonNode> readGeData(String csvFile) throws Exception {
		List<JsonNode> generalEventsArray = new ArrayList<JsonNode>();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(csvFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		BufferedReader reader = new BufferedReader(fileReader);
		try {
			String data = "";
			while ((data = reader.readLine()) != null) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode map = mapper.readValue(data, ObjectNode.class);
				generalEventsArray.add(map);
			}
			LOGGER.info("Completed reading General Events Details");
			return generalEventsArray;
		} finally {
			reader.close();
		}
	}

}
