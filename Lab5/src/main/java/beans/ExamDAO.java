package beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Exam;

public class ExamDAO {
	 
    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory("pgPU").createEntityManager();
    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
// 
    // Method To Fetch All Student Details From The Database
    @SuppressWarnings("unchecked")
    public static List getAllExamDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Exam s");
        List examList = queryObj.getResultList();
        if (examList != null && examList.size() > 0) {           
            return examList;
        } else {
        	System.out.println("NOOOO");
        	return null;
            
        }
    }
 
    
    public static Exam createNewExam(String name) {
        if(!entityMgrObj.getTransaction().isActive()) {
        	entityMgrObj.getTransaction().begin();
        }
 
        Exam newExamObj = new Exam();
        newExamObj.setName(name);
        entityMgrObj.persist(newExamObj);
        entityMgrObj.getTransaction().commit();
        return newExamObj;
    }
 
     
    public static void deleteExamDetails(int examId) {
        if (!transactionObj.isActive()) {
            transactionObj.begin();
        }
 
        Exam deleteExamObj = new Exam();
       
        deleteExamObj.setId(examId);
        entityMgrObj.remove(entityMgrObj.merge(deleteExamObj));
               
        transactionObj.commit();
        
    }
 
    
    public static void updateExamDetails(int examId, String updatedExamName) {
        if (!transactionObj.isActive()) {
            transactionObj.begin();
        }
 
            Query queryObj = entityMgrObj.createQuery("UPDATE Exam s SET s.name=:name WHERE s.id= :id");         
            queryObj.setParameter("id", examId);
            queryObj.setParameter("name", updatedExamName);
            int updateCount = queryObj.executeUpdate();
            if(updateCount > 0) {
                System.out.println("Record For Id: " + examId + " Is Updated");
            }
        
        transactionObj.commit();
    }
}
