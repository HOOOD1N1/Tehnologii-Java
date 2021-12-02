package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Exam;


@ManagedBean
public class ExamRepository {
	
	 @EJB
	 ExamDAO examDAO;
	 
	private Exam exam;
	
	public Exam getExam() {
		return exam;
	}


	public void setExam(Exam exam) {
		this.exam = exam;
	}


	public ExamRepository() {		
	}
	

	public Exam addExam(Exam e) {
		return examDAO.createNewExam(e.getName());
	}
	
	public void update(Exam e) {
		examDAO.updateExamDetails(e.getId(), e.getName());	
	}
	
	
	@SuppressWarnings("unchecked")
	public List allExams(){
		return examDAO.getAllExamDetails(); 
	}
	
	

}
