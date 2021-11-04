package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class StudentsList {
	
	private final String url = "jdbc:postgresql://localhost:5432/exams";
	private final String user = "postgres";
	private final String password = "postgres";
	
	private List<Student> listOfStudents = null;
	
	public List<Student> getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(List<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}

	public StudentsList() {
		listOfStudents = new ArrayList<Student>();
		Connection conn = null;
		
		 Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         conn = DriverManager
	            .getConnection(url,
	            user, password);
	         
	         System.out.println("It works for students");
	         
	         stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * from students;");
	         while(rs.next()) {
	        	 listOfStudents.add(new Student(rs.getString("name")));
	         }
	         stmt.close();
	         conn.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	      }
	}
	
	
}
