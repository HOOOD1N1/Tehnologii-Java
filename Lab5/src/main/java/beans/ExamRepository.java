package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Exam;
import entity.Student;


@ManagedBean
public class ExamRepository {
	private int id;
    private String name; 
	
	public ExamRepository() {		
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

	public Exam addExam(Exam e) {
		return ExamDAO.createNewExam(e.getName());
	}
	
	public void update(Exam e) {
		ExamDAO.updateExamDetails(e.getId(), e.getName());	
	}
	
	
	@SuppressWarnings("unchecked")
	public List allExams(){
		return ExamDAO.getAllExamDetails(); 
	}
	
	

}
