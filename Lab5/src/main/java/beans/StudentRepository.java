package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Student;

@ManagedBean
public class StudentRepository {
	
    private int id;
    private String name; 
	
	public StudentRepository() {		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student addStudent(Student s) {
		return StudentDAO.createNewStudent(s.getName());
	}
	
	public void update(Student student) {
		StudentDAO.updateStudentDetails(student.getId(), student.getName());	
	}
	
	
	@SuppressWarnings("unchecked")
	public List allStudents(){
		return StudentDAO.getAllStudentDetails(); 
	}
	
}
