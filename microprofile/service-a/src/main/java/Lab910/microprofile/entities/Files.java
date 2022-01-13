package Lab910.microprofile.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the files database table.
 * 
 */
@Entity
@Table(name="files")
@NamedQueries({
	@NamedQuery(name = "findById", query = "SELECT c FROM Files c WHERE c.id = :id"),
	@NamedQuery(name="findAll", query="SELECT f FROM Files f")
	
})
public class Files implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private byte[] file;
	
	@Column(name="name")
	private String name;

	@Column(name="registration_number")
	private Integer registrationNumber;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;

	public Files() {
	}
	
	public Files(int x) {
		this.registrationNumber = (Integer)x; 	
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	public void setUserId(Integer id) {
		this.user.setId(id);
	}

}