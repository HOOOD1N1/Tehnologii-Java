package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Named;

import entities.Users;

@LocalBean
@Stateful
@Named
public class UsersRepository {

	@EJB
	UsersDAO usersDAO;


	public Users addResource(Users e) {
		return usersDAO.createNewUser(e);
	}
	
	public Integer getDbId(String name) {
		return usersDAO.getIdByUserName(name);
	}
	
	public List<Users> allResources(){
		return usersDAO.getAllUsers(); 
	}

}
