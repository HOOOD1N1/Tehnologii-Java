package beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Student;

public class StudentDAO {
  
	    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory("pgPU").createEntityManager();
	    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
//	 
	    
	    @SuppressWarnings("unchecked")
	    public static List getAllStudentDetails() {
	        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Student s");
	        List studentList = queryObj.getResultList();
	        if (studentList != null && studentList.size() > 0) {           
	            return studentList;
	        } else {
	        	System.out.println("NOOOO");
	        	return null;
	            
	        }
	    }
	 
	    
	    public static Student createNewStudent(String name) {
	        if(!transactionObj.isActive()) {
	            transactionObj.begin();
	        }
	 
	        Student newStudentObj = new Student();
	        newStudentObj.setName(name);
	        entityMgrObj.persist(newStudentObj);
	        transactionObj.commit();
	        return newStudentObj;
	    }
	 
	     
	    public static void deleteStudentDetails(int studentId) {
	        if (!transactionObj.isActive()) {
	            transactionObj.begin();
	        }
	 
	        Student deleteStudentObj = new Student();
	       
	        deleteStudentObj.setId(studentId);
	        entityMgrObj.remove(entityMgrObj.merge(deleteStudentObj));
	               
	        transactionObj.commit();
	        
	    }
	 
	    // Method To Update The Student Details For A Particular Student Id In The Database
	    public static void updateStudentDetails(int studentId, String updatedStudentName) {
	        if (!transactionObj.isActive()) {
	            transactionObj.begin();
	        }
	 
	            Query queryObj = entityMgrObj.createQuery("UPDATE Student s SET s.name=:name WHERE s.id= :id");         
	            queryObj.setParameter("id", studentId);
	            queryObj.setParameter("name", updatedStudentName);
	            int updateCount = queryObj.executeUpdate();
	            if(updateCount > 0) {
	                System.out.println("Record For Id: " + studentId + " Is Updated");
	            }
	        
	        transactionObj.commit();
	    }
	}

