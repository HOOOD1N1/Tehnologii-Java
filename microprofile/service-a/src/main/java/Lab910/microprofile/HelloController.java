package Lab910.microprofile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import Lab910.microprofile.entities.Files;
import Lab910.microprofile.entities.Users;
import beans.FilesRepository;
import beans.HelloControllerServiceFallback;



/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

	@Inject
	FilesRepository filesRepo;
	
    @GET
    public String sayHello() {
        return "Hello World";
    }
    
    @Path("/show")
	@GET
	public String show() {
		return "it works";
	}
	
	@Path("/addDocumentService")
	@Counted(
			name="uploadFileStatus",
			monotonic=false,
			displayName="uploadFile Status"
			
			)
	@CircuitBreaker(
			successThreshold = 5,
			requestVolumeThreshold = 8,
			failureRatio=0.75,
			delay = 1000)
	@Fallback(HelloControllerServiceFallback.class)
	@Bulkhead(value=5, waitingTaskQueue=5)
	@Retry(delay=400, maxRetries = 10, retryOn= {IOException.class})
	@Timeout(value=3,unit=ChronoUnit.SECONDS)
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
	@Timed(name = "deleteFile",
    description = "Metrics to monitor the times of deleteFile method.",
    unit = MetricUnits.MINUTES,
    absolute = true)
	@Bulkhead(5)
	@Fallback(HelloControllerServiceFallback.class)
	@Timeout(value=3,unit=ChronoUnit.SECONDS)
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
	@Bulkhead(5)
	@Fallback(HelloControllerServiceFallback.class)
	@Timeout(value=3,unit=ChronoUnit.SECONDS)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	 public List<String> viewFiles(  
			@NotNull @FormDataParam("id") int id, @Context HttpServletRequest request, @Context HttpServletResponse response) {  
		
		return filesRepo.allResources();
	}
	
	@Path("/updateDocumentService")
	@Bulkhead(value=5, waitingTaskQueue=5)
	@Fallback(HelloControllerServiceFallback.class)
	@Retry(delay=400, maxRetries = 10, retryOn= {IOException.class})
	@Timeout(value=3,unit=ChronoUnit.SECONDS)
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
