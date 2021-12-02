package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Resource;

@LocalBean
@Stateless
public class ResourcesDAO {
	@PersistenceContext
    private EntityManager entityMgrObj;
   
// 
    // Method To Fetch All Student Details From The Database
    @SuppressWarnings("unchecked")
    public List getAllResourceDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Resource s");
        List resourceList = queryObj.getResultList();
        if (resourceList != null && resourceList.size() > 0) {           
            return resourceList;
        } else {
        	System.out.println("NOOOO");
        	return null;
            
        }
    }
 
    
    public Resource createNewResource(String name) {
    	Resource newResourceObj = new Resource();
        newResourceObj.setName(name);
        entityMgrObj.merge(newResourceObj);
        
        return newResourceObj;
    }
 
     
    public void deleteResourceDetails(int resourceId) {
       
    	Resource deleteResourceObj = new Resource();
       
        deleteResourceObj.setId(resourceId);
        entityMgrObj.remove(entityMgrObj.merge(deleteResourceObj));
        
    }
 
    
    public void updateResourceDetails(int resourceId, String updatedResourceName) {

            Query queryObj = entityMgrObj.createQuery("UPDATE Resource s SET s.name=:name WHERE s.id= :id");         
            queryObj.setParameter("id", resourceId);
            queryObj.setParameter("name", updatedResourceName);
            int updateCount = queryObj.executeUpdate();
            if(updateCount > 0) {
                System.out.println("Record For Id: " + resourceId + " Is Updated");
            }
    }
}
