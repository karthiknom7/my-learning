package my.kk.mongoExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		MongoCredential credential = MongoCredential.createCredential("admin", "admin", "rjio@harman".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));

		MongoDatabase db = mongoClient.getDatabase("test");
		System.out.println("Got the DB");
		MongoCollection<Document> postCollection = db.getCollection("post");
		//List<Document> reslut = postCollection.find().into(new ArrayList<>());
		//System.out.println(reslut);
		db.listCollectionNames().into(new ArrayList<>()).stream().forEach(System.out::println);
	}
}
