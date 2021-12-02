package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the resources database table.
 * 
 */
@Entity
@Table(name="resources")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;
	


	private Integer available;

	//bi-directional many-to-one association to ResourcesUsed
	@OneToMany(mappedBy="resource")
	private List<ResourcesUsed> resourcesUseds;

	public Resource() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public List<ResourcesUsed> getResourcesUseds() {
		return this.resourcesUseds;
	}

	public void setResourcesUseds(List<ResourcesUsed> resourcesUseds) {
		this.resourcesUseds = resourcesUseds;
	}

	public ResourcesUsed addResourcesUsed(ResourcesUsed resourcesUsed) {
		getResourcesUseds().add(resourcesUsed);
		resourcesUsed.setResource(this);

		return resourcesUsed;
	}

	public ResourcesUsed removeResourcesUsed(ResourcesUsed resourcesUsed) {
		getResourcesUseds().remove(resourcesUsed);
		resourcesUsed.setResource(null);

		return resourcesUsed;
	}

}