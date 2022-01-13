package beans;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import javax.inject.Inject;
import javax.inject.Named;

import Lab910.microprofile.entities.Files;




@Named

public class FilesRepository {

	@Inject
	FilesDAO filesDAO;

	
	public Files addResource(Files e) {
		return filesDAO.createNewFile(e);
	}
	
	
	public List<String> allResources(){
		return filesDAO.getAllFiles(); 
	}
	
	
	public void deleteFile(int id) {
		filesDAO.deleteFilesDetails(id);
	}
	
	
	public void updateFile(int id, byte[] newFile, String name) {
		Files filesToUpdate = filesDAO.findById(id);
        

		filesToUpdate.setFile(newFile);
		filesToUpdate.setName(name);
		filesToUpdate.setUserId(id);
		
        filesDAO.update(filesToUpdate);

	}
}
