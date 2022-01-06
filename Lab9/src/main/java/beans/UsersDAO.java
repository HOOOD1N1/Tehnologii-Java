package beans;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entities.Users;



@LocalBean
@Stateless
@DeclareRoles({"admin", "author"})
public class UsersDAO {
	
	@PersistenceContext
    private EntityManager entityMgrObj;
	
	@RolesAllowed({"user", "admin"})
	@SuppressWarnings("unchecked")
	public List<Users> getAllUsers() {
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Users s");
        List<Users> usersList = queryObj.getResultList();
        if (usersList != null && usersList.size() > 0) {           
            return usersList;
        } else {
        	System.out.println("NOOOO");
        	return null;
            
        }
    }
	
	@Transactional
    public Users createNewUser(Users e) {
    	
        this.entityMgrObj.merge(e);
        
        return e;
    }
	
	@RolesAllowed({"user", "admin"})
    public void deleteUserDetails(int userId) {
        
    	Users deleteResourceObj = new Users();
       
        deleteResourceObj.setId(userId);
        entityMgrObj.remove(entityMgrObj.merge(deleteResourceObj));
        
    }
    
	@RolesAllowed({"user", "admin"})
	public Integer getIdByUserName(String name) {
        TypedQuery<Integer> queryObj = entityMgrObj.createQuery("SELECT s.id FROM Users AS s WHERE s.name= ?1", Integer.class);
        Integer id = queryObj.setParameter(1, name).getSingleResult();

        if (id != null) {           
            return id;
        } else {
        	System.out.println("NOOOO");
        	return null;
            
        }
    }
	
}
