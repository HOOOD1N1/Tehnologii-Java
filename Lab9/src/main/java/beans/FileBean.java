package beans;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import entities.Files;
import entities.Files;

@Named
@Stateful
@SessionScoped
@DeclareRoles({"author", "admin"})
public class FileBean {

	private UploadedFile file;
	
	@EJB
	FilesRepository filesRepo;
	
	Files createdFiles;
	
	public UploadedFile getFile() {
		return file;
	}
	
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public FileBean() {

	}
	@RolesAllowed("author")
	public String upload() {
		if (file != null) {
		try {
			InputStream input = file.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			
			for(int i = 0; (i=input.read(buffer))>0;) {
				output.write(buffer, 0, i);
			}
			
			createdFiles.setFile(output.toByteArray());
			
			RandomString rs = new RandomString();
			Random rand = new Random();
			
			
			createdFiles.setRegistrationNumber(rand.nextInt(1200));
			
			filesRepo.addResource(createdFiles);
			
		}catch(Exception e) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
		}
		
		return "index";
	}
	
}
