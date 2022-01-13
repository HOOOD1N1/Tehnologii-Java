package Lab910.microprofile.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "username")
	private String username;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="user")
	private List<Files> files;

	public Users() {
	}
	
	public Users(String userName, String password, String role) {
		
		this.username = userName;
		this.password = password;
		this.role = role;
	}
	
	public Users(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Files> getFiles() {
		return this.files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

	public Files addFile(Files file) {
		getFiles().add(file);
		file.setUser(this);

		return file;
	}

	public Files removeFile(Files file) {
		getFiles().remove(file);
		file.setUser(null);

		return file;
	}

}