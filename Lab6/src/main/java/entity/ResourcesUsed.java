package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resources_used database table.
 * 
 */
@Entity
@Table(name="resources_used")
@NamedQuery(name="ResourcesUsed.findAll", query="SELECT r FROM ResourcesUsed r")
public class ResourcesUsed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Exam
	@ManyToOne
	@JoinColumn(name="exam_name", referencedColumnName="name")
	private Exam exam;

	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="resource_name", referencedColumnName="name")
	private Resource resource;

	public ResourcesUsed() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}