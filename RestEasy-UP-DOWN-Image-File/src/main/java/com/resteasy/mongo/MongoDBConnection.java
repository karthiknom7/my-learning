package com.resteasy.mongo;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.jboss.resteasy.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.resteasy.util.PropertiesReader;

/**
 * This class responsible for getting mongo connection
 * 
 * @author KNarayanaswa
 *
 */
public class MongoDBConnection {
	private static final Logger LOGGER = Logger.getLogger(MongoDBConnection.class);

	private static MongoClient mongoClient;

	private MongoDBConnection() {
	};

	/**
	 * Returns mongo connection 
	 * 
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException 
	 */
	public static MongoClient getConnection() throws IOException {
		LOGGER.debug("Getting mongo connection ");
		if (mongoClient == null) {
			synchronized (MongoDBConnection.class) {
				if (mongoClient == null) {
					LOGGER.info("Creating mongo client....");
					String mongoUsername = PropertiesReader.MONGO_USERNAME;
					String mongoPassword = PropertiesReader.MONGO_PASSWORD;
					String mongoHost = PropertiesReader.MONGO_HOST;
					int mongoPort = Integer.parseInt(PropertiesReader.MONGO_PORT);
					String adminDb = PropertiesReader.MONGO_ADMIN_DB;
					MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
					builder.connectionsPerHost(5000).codecRegistry(com.mongodb.MongoClient.getDefaultCodecRegistry());
					MongoClientOptions options = builder.build();
					
					MongoCredential mongoCred = MongoCredential.createCredential(mongoUsername, adminDb,
							mongoPassword.toCharArray());
					mongoClient = new MongoClient(new ServerAddress(mongoHost, mongoPort),
							Arrays.asList(mongoCred),options);
					LOGGER.info("MongoClient successfully created...");
				}
			}
		}
		return mongoClient;
	}
}
