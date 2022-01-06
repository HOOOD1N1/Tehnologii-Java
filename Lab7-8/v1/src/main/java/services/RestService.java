package services;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

//@Path("/MyRestService")
@ApplicationPath("/resources")
public class RestService extends Application{

	public Set<Class<?>> getClasses(){
		 final Set<Class<?>> resources = new HashSet<Class<?>>();

	        // Add your resources.
	        resources.add(DocumentService.class);

	        // Add additional features such as support for Multipart.
	        resources.add(MultiPartFeature.class);
	        resources.add(Response.class);
	        resources.add(InputStream.class);

	        return resources;
	    }
	}