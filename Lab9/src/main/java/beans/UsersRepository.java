package beans;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Named;

import entities.Users;

@DeclareRoles({"admin", "author"})
@LocalBean
@Stateful
@Named
public class UsersRepository {

	@EJB
	UsersDAO usersDAO;

	@RolesAllowed({"user", "admin"})
	public Users addResource(Users e) {
		return usersDAO.createNewUser(e);
	}
	
	@RolesAllowed({"user", "admin"})
	public Integer getDbId(String name) {
		return usersDAO.getIdByUserName(name);
	}
	
	@RolesAllowed({"user", "admin"})
	public List<Users> allResources(){
		return usersDAO.getAllUsers(); 
	}

}
