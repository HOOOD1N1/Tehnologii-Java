package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

@ManagedBean(name="studentList")
public class StudentsList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/exams")
	private DataSource ds2;
	
	private List<Student> listOfStudents = null;
	
	public List<Student> getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(List<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}

	public StudentsList() throws SQLException {
		listOfStudents = new ArrayList<Student>();
		Connection con1 = null;
		PreparedStatement pstmt1 = null;
		try {
			 con1 = ds2.getConnection("postgres", "postgres");
			 pstmt1 = con1.prepareStatement("SELECT * from students;");
			 ResultSet rs = pstmt1.executeQuery();
			 while(rs.next()) {
	        	 listOfStudents.add(new Student(rs.getString("name")));
	         }
			 
			 rs.close();
			 pstmt1.close();
		 } finally {
			 if (con1 != null) con1.close();
		 }
//	      try {
//	         Class.forName("org.postgresql.Driver");
//	         conn = DriverManager
//	            .getConnection(url,
//	            user, password);
//	         
//	         System.out.println("It works for students");
//	         
//	         stmt = conn.createStatement();
//	         ResultSet rs = stmt.executeQuery("SELECT * from students;");
//	         while(rs.next()) {
//	        	 listOfStudents.add(new Student(rs.getString("name")));
//	         }
//	         stmt.close();
//	         conn.close();
//	      } catch ( Exception e ) {
//	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//	      }
	}
	
	
}
