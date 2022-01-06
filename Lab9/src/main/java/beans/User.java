package beans;

import java.io.Serializable;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entities.Users;
@DeclareRoles({"admin", "author"})
@Named
@SessionScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class User implements Serializable {
		
	private static final long serialVersionUID = 1L;

		private Integer id;
	
		@NotNull
		private String name;
		
		@NotNull
		private String password;
		
		private String role;
		
		private String error;
		
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		@EJB
		UsersRepository usersRepo;
		
		public User() {
			this.name = "";
			this.password = "";
			this.role = "";
			this.error = "";
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = this.getDBId();
		}
		
		public Integer getDBId() {
			return usersRepo.getDbId(this.name);
		}
		
		public String toLogIn() {
			return "index";
		}
		@RolesAllowed("user")
		public String toSignUp() {
			return "SignUp";
		}
		@RolesAllowed({"user", "admin"})
		public String logIn() {
			FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			System.out.println("Enters");

			boolean exists = false;
			for(int i = 0; i < usersRepo.allResources().size(); i++) {
				
				if(usersRepo.allResources().get(i).getPassword().equals(password) && usersRepo.allResources().get(i).getUsername().equals(name)) {
					exists = true;
					break;
				}
			}
			if(exists) {
				if("Author".equals(this.getRole())) {
					return "showSubmissions.xhtml?faces-redirect=true";
				}else return "main.xhtml?faces-redirect=true";
			
			} else {
				this.error = "Please enter a correct username/password";
				return "index.xhtml?faces-redirect=true";
				
			}
		}
		@RolesAllowed({"user", "admin"})
		public String signUp() {
			usersRepo.addResource(new Users(name, password, role));
			return "main";
		}
		@RolesAllowed({"user", "admin"})
		public String show() {
			Client client = ClientBuilder.newClient();
			WebTarget myResource = client.target("http://localhost:4949/v1/resources/services/show");
			String response = myResource.request(MediaType.TEXT_PLAIN).get(String.class); 
			
			client.close();
			return response;
		}
		
}

