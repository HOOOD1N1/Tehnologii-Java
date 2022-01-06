package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Files;

@LocalBean
@Stateless
public class FilesDAO {

	@PersistenceContext
    private EntityManager entityMgrObj;
	
    public Files findById(int filesId) {
    	Files files = entityMgrObj.find(Files.class, Integer.valueOf(filesId));
        if (files == null) {
            throw new EntityNotFoundException("Can't find Files for ID "
                    + filesId);
        }
        return files;
    }
	
	@SuppressWarnings("unchecked")
	public List<String> getAllFiles() {
		TypedQuery<String> queryObj = entityMgrObj.createQuery("SELECT s.name FROM Files AS s", String.class);
        List<String> filesList = queryObj.getResultList();
        if (filesList != null && filesList.size() > 0) {           
            return filesList;
        } else {
        	System.out.println("NOOOO");
        	return null;
            
        }
    }
	
    public void save(Files files) {
    	
    	this.entityMgrObj.persist(files);
    	
    }
	
    public void update(Files files) {
    	
    	this.entityMgrObj.merge(files);
    	
    }
	
    public Files createNewFile(Files e) {
    	
        this.entityMgrObj.merge(e);
        
        return e;
    }
	
    public void deleteFilesDetails(int id) {
        
    	List files = entityMgrObj.createNamedQuery("findById").setParameter("id", id).setMaxResults(1).getResultList();
        Files fileToDelete = (Files) files.get(0);
    	entityMgrObj.remove(entityMgrObj.merge(fileToDelete));
        
    }
}
