package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Student;

@LocalBean
@Stateless
public class StudentDAO {
		
		@PersistenceContext
		EntityManager entityMgrObj;
	    
//	 
	    
	    @SuppressWarnings("unchecked")
	    public List getAllStudentDetails() {
	        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Student s");
	        List studentList = queryObj.getResultList();
	        if (studentList != null && studentList.size() > 0) {           
	            return studentList;
	        } else {
	        	System.out.println("NOOOO");
	        	return null;
	            
	        }
	    }
	 
	    
	    public Student createNewStudent(String name) {
	        Student newStudentObj = new Student();
	        newStudentObj.setName(name);
	        entityMgrObj.merge(newStudentObj);
	        return newStudentObj;
	    }
	 
	     
	    public void deleteStudentDetails(int studentId) {

	        Student deleteStudentObj = new Student();
	       
	        deleteStudentObj.setId(studentId);
	        entityMgrObj.remove(entityMgrObj.merge(deleteStudentObj));

	        
	    }
	 
	    // Method To Update The Student Details For A Particular Student Id In The Database
	    public void updateStudentDetails(int studentId, String updatedStudentName) {

	            Query queryObj = entityMgrObj.createQuery("UPDATE Student s SET s.name=:name WHERE s.id= :id");         
	            queryObj.setParameter("id", studentId);
	            queryObj.setParameter("name", updatedStudentName);
	            int updateCount = queryObj.executeUpdate();
	            if(updateCount > 0) {
	                System.out.println("Record For Id: " + studentId + " Is Updated");
	            }
	        
	    }
	}

