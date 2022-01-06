package services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.primefaces.model.file.UploadedFile;

import beans.FilesRepository;
import beans.RandomString;
import entities.Files;
import entities.Users;

@Path("/services")
public class DocumentService {
	
	@EJB
	FilesRepository filesRepo;
	
	
	@Path("/show")
	@GET
	public String show() {
		return "it works";
	}
	
	@Path("/addDocumentService")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	 public Response uploadFile(  
			@NotNull @FormDataParam("id") Integer id,  @FormDataParam("file") InputStream uploadedStream,@FormDataParam("file") FormDataContentDisposition fileDetail, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {  
		
		if(uploadedStream != null && id != null) {
		String name = fileDetail.getFileName();
		Files createdFiles = new Files(1122);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		
		for(int i = 0; (i=uploadedStream.read(buffer))>0;) {
			output.write(buffer, 0, i);
		}
		
		Users user = new Users(id);
		
		createdFiles.setFile(output.toByteArray());
		createdFiles.setName(name);
		createdFiles.setUser(user);
		
		Random rand = new Random();
		
		
		createdFiles.setRegistrationNumber(rand.nextInt(1200));
		
		filesRepo.addResource(createdFiles);
		String myJsfPage = "/index.xhtml";
	    String contextPath = request.getContextPath();
	    response.sendRedirect(contextPath + myJsfPage);
		
	    return Response.status(Response.Status.OK).build();  
	        } else {
	        	 return Response.status(Response.Status.BAD_REQUEST).build();
	        }
	}
	
	
	@Path("/deleteDocumentService")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	 public Response deleteFile(  
			@NotNull @FormDataParam("id") int id, @Context HttpServletRequest request, @Context HttpServletResponse response) {  
		try {
		filesRepo.deleteFile(id);
		
		String myJsfPage = "/index.xhtml";
	    String contextPath = request.getContextPath();
	    response.sendRedirect(contextPath + myJsfPage);
		
	    return Response.status(Response.Status.OK).build();  
		}catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@Path("/viewDocumentService")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	 public List<String> viewFiles(  
			@NotNull @FormDataParam("id") int id, @Context HttpServletRequest request, @Context HttpServletResponse response) {  
		
		return filesRepo.allResources();
	}
	
	@Path("/updateDocumentService")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	 public Response updateExistentFile(  
			@FormDataParam("id") int id, @FormDataParam("file") InputStream uploadedStream, @FormDataParam("file") FormDataContentDisposition fileDetail, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {  
		
		if(uploadedStream != null) {
			String name = fileDetail.getFileName();
			
			ByteArrayOutputStream output = new ByteArrayOutputStream(); 
			
			byte[] buffer = new byte[1024];
			
			for(int i = 0; (i=uploadedStream.read(buffer))>0;) {
				output.write(buffer, 0, i);
			}
			
			
			
			filesRepo.updateFile(id, output.toByteArray(), name);
			
			String myJsfPage = "/index.xhtml";
		    
			String contextPath = request.getContextPath();
		    response.sendRedirect(contextPath + myJsfPage);
		    
			
		    return Response.status(Response.Status.OK).build();
		        } else { 
		        	return Response.status(Response.Status.BAD_REQUEST).build();
		        }
	}
	        
}
