package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

@ManagedBean
public class Student {

	private String name;
	private List<String> exams = null;
	
	@Resource(name="jdbc/exams")
	private DataSource ds;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getExams() {
		return exams;
	}
	public void setExams(String exam) {
		this.exams.add(exam);
	}
	public Student() {
		exams = new ArrayList<String>();
	}
	public Student(String name) {
		this.name = name;
		System.out.println(name);
	}
	public String add() throws SQLException {
		System.out.println("It enters the add");
		Connection con = null;
	      PreparedStatement pstmt = null;
	      try {
				 con = ds.getConnection("postgres", "postgres");
				 for(String i: exams ) {
				 pstmt = con.prepareStatement("INSERT INTO students (name, exam_name)" + " VALUES (?, ?)");
				 pstmt.setString(1, name);
		         pstmt.setString(2, i);
		         
		         pstmt.execute();
		         pstmt.close();
				 }
		         con.commit();

			 } finally {
				 if (con != null) con.close();
				 ds = null;
			 }
//	      try {
//	         Class.forName("org.postgresql.Driver");
//	         c = DriverManager
//	            .getConnection("jdbc:postgresql://localhost:5432/exams",
//	            "postgres", "postgres");
//	         c.setAutoCommit(false);
//	         System.out.println("Opened database successfully");
//	         
//	         for(String i: exams ) {
//	         stmt = c.createStatement();
//	         String sql = "INSERT INTO students (name,exam_name) "
//	            + "VALUES (" + name + "," + i + ");";
//	         stmt.executeUpdate(sql);
//	         stmt.close();
//	         }
//	         c.commit();
//	         c.close();
//	      } catch (Exception e) {
//	    	  System.out.println("Error");
//	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//	         System.exit(0);
//	      }
	      System.out.println("Records created successfully");
	      return "studentsList";
	}
	
}
