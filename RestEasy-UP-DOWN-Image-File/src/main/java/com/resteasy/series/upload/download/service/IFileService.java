package com.resteasy.series.upload.download.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("/fileservice")
public interface IFileService {

	// http://localhost:8080/RestEasy-UP-DOWN-Image-File/resteasy/fileservice/download/image  - Tomcat 7.0.x
	// http://localhost:9090/RestEasy-UP-DOWN-Image-File/resteasy/fileservice/download/image  - JBoss AS7
	@GET
	@Path("/download/image/{id}")
	@Produces({"image/png", "image/jpg", "image/gif"})
	public Response downloadImageFile(@PathParam("id") String id);

	// http://localhost:8080/RestEasy-UP-DOWN-Image-File/resteasy/fileservice/upload/images  - Tomcat 7.0.x
	// http://localhost:9090/RestEasy-UP-DOWN-Image-File/resteasy/fileservice/upload/images  - JBoss AS7
	@POST
	@Path("/upload/images")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadImageFile(MultipartFormDataInput multipartFormDataInput);
}