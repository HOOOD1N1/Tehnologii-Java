package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;

import entity.Resource;

@LocalBean
@Stateful
@ManagedBean
public class ResourcesRepository {

	@EJB
	ResourcesDAO resourcesDAO;
	
	@EJB
	AssignmentMap assignmentMap;
	
	private String examName;
	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	private List<String> resourceNames;
	private List<String> selectedResourceNames;
	
	public List<String> getSelectedResourceNames() {
		return selectedResourceNames;
	}

	public void setSelectedResourceNames(List<String> selectedResourceNames) {
		this.selectedResourceNames = selectedResourceNames;
	}

	public List<String> getResourceNames() {
		return resourceNames;
	}

	public void setResourceNames(List<String> resourceNames) {
		this.resourceNames = resourceNames;
	}

	public Resource addResource(Resource e) {
		return resourcesDAO.createNewResource(e.getName());
	}
	
	public void update(Resource e) {
		resourcesDAO.updateResourceDetails(e.getId(), e.getName());	
	}
	
	
	public List allResources(){
		return resourcesDAO.getAllResourceDetails(); 
	}
	
	public String addAssignment() {
		System.out.println("added");
		assignmentMap.add(examName, selectedResourceNames);
		
		return "/index.xhtml";
	}

}
