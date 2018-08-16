package com.resteasy.series.upload.download.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.resteasy.mongo.MongoDBConnection;
import com.resteasy.util.PropertiesReader;

public class FileServiceImpl implements IFileService {

	public Response downloadImageFile(String id) {
		System.out.println("ID " + id);
		return getFileFromMongo(id);
	}

	public Response uploadImageFile(MultipartFormDataInput multipartFormDataInput) {

		// local variables
		MultivaluedMap<String, String> multivaluedMap = null;
		String fileName = null;
		InputStream inputStream = null;
		String uploadFilePath = null;
		Response response = null;
		try {
			Map<String, List<InputPart>> map = multipartFormDataInput.getFormDataMap();
			List<InputPart> lstInputPart = map.get("uploadedFile");

			for (InputPart inputPart : lstInputPart) {

				// get filename to be uploaded
				multivaluedMap = inputPart.getHeaders();
				fileName = getFileName(multivaluedMap);

				if (null != fileName && !"".equalsIgnoreCase(fileName)) {

					// write & upload file to UPLOAD_FILE_SERVER
					inputStream = inputPart.getBody(InputStream.class, null);
					uploadFilePath = writeFileToMongo(inputStream, fileName);

					// close the stream
					inputStream.close();
				}
			}
			response = Response.ok("Image uploaded successfully.  Image ID is " + uploadFilePath).build();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(ioe.getMessage()).build();
		} finally {
			// release resources, if any
		}
		return response;
	}

	/**
	 * Write file to Mongo DB
	 * 
	 * @param inputStream
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private String writeFileToMongo(InputStream inputStream, String fileName) throws IOException {

		MongoClient mongoClient = MongoDBConnection.getConnection();
		DB mongoDB = mongoClient.getDB(PropertiesReader.MONGO_DB_NAME);
		DBCollection collection = mongoDB.getCollection(PropertiesReader.MONGO_IMAGE_COLLECTION_NAME);

		BasicDBObject document = new BasicDBObject();

		// document.append("_id", "123"+fileName);
		document.append("filename", fileName);
		collection.insert(document);

		GridFS fileStore = new GridFS(mongoDB, PropertiesReader.MONGO_IMAGE_COLLECTION_NAME);
		GridFSInputFile inputFile = fileStore.createFile(inputStream);
		String id = document.getObjectId("_id").toHexString();
		inputFile.setId(document.getObjectId("_id"));
		inputFile.setFilename(fileName);
		inputFile.save();

		return id;

	}

	/**
	 * Read file from Mongo DB
	 * 
	 * @param id
	 * @return
	 */
	private Response getFileFromMongo(String id) {
		Response response = null;
		MongoClient mongoClient;
		try {
			mongoClient = MongoDBConnection.getConnection();
			DB mongoDB = mongoClient.getDB(PropertiesReader.MONGO_DB_NAME);
			DBCollection collection = mongoDB.getCollection(PropertiesReader.MONGO_IMAGE_COLLECTION_NAME);

			BasicDBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(id));

			DBObject doc = collection.findOne(query);
			if (doc != null) {
				GridFS fileStore = new GridFS(mongoDB, PropertiesReader.MONGO_IMAGE_COLLECTION_NAME);
				GridFSDBFile gridFile = fileStore.findOne(query);

				InputStream in = gridFile.getInputStream();

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int data = in.read();
				while (data >= 0) {
					out.write((char) data);
					data = in.read();
				}
				out.flush();

				ResponseBuilder builder = Response.ok(out.toByteArray());
				builder.header("Content-Disposition", "attachment; filename=" + doc.get("filename"));
				response = builder.build();
			} else {
				System.out.println("Image not found in mongo");
				ResponseBuilder builder = Response.ok("File not exists");
				response = builder.build();
			}

		} catch (IOException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	/**
	 * 
	 * @param multivaluedMap
	 * @return
	 */
	private String getFileName(MultivaluedMap<String, String> multivaluedMap) {

		String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {

			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String exactFileName = name[1].trim().replaceAll("\"", "");
				return exactFileName;
			}
		}
		return "UnknownFile";
	}
}