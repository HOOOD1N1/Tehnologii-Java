package beans;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Named;

import entities.Files;

@LocalBean
@Stateful
@Named
@DeclareRoles({"admin", "author"})
public class FilesRepository {

	@EJB
	FilesDAO filesDAO;

	@RolesAllowed({"user", "admin"})
	public Files addResource(Files e) {
		return filesDAO.createNewFile(e);
	}
	
	@RolesAllowed({"user", "admin"})
	public List<String> allResources(){
		return filesDAO.getAllFiles(); 
	}
	
	@RolesAllowed({"user", "admin"})
	public void deleteFile(int id) {
		filesDAO.deleteFilesDetails(id);
	}
	
	@RolesAllowed({"user", "admin"})
	public void updateFile(int id, byte[] newFile, String name) {
		Files filesToUpdate = filesDAO.findById(id);
        

		filesToUpdate.setFile(newFile);
		filesToUpdate.setName(name);
		filesToUpdate.setUserId(id);
		
        filesDAO.update(filesToUpdate);

	}
}
