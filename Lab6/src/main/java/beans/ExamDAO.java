package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Exam;

@LocalBean
@Stateless
public class ExamDAO {
	 
	@PersistenceContext
    private EntityManager entityMgrObj;
   
// 
    // Method To Fetch All Student Details From The Database
    @SuppressWarnings("unchecked")
    public List getAllExamDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Exam s");
        List examList = queryObj.getResultList();
        if (examList != null && examList.size() > 0) {           
            return examList;
        } else {
        	System.out.println("NOOOO");
        	return null;
            
        }
    }
 
    
    public Exam createNewExam(String name) {
        Exam newExamObj = new Exam();
        newExamObj.setName(name);
        entityMgrObj.merge(newExamObj);
        
        return newExamObj;
    }
 
     
    public void deleteExamDetails(int examId) {
       
        Exam deleteExamObj = new Exam();
       
        deleteExamObj.setId(examId);
        entityMgrObj.remove(entityMgrObj.merge(deleteExamObj));
        
    }
 
    
    public void updateExamDetails(int examId, String updatedExamName) {

            Query queryObj = entityMgrObj.createQuery("UPDATE Exam s SET s.name=:name WHERE s.id= :id");         
            queryObj.setParameter("id", examId);
            queryObj.setParameter("name", updatedExamName);
            int updateCount = queryObj.executeUpdate();
            if(updateCount > 0) {
                System.out.println("Record For Id: " + examId + " Is Updated");
            }
    }
}
