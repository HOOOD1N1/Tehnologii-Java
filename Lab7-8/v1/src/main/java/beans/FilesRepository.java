package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Named;

import entities.Files;

@LocalBean
@Stateful
@Named
public class FilesRepository {

	@EJB
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
