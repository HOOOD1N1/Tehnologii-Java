package beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

@ManagedBean(name="exam")
public class Exam {
	
	private String name;
	private Date timeOfStart;
	private int duration;
	
	@Resource(name="jdbc/exams")
	private DataSource ds;
	
	public Exam() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTimeOfStart() {
		return timeOfStart;
	}
	public void setTimeOfStart(Date timeOfStart) {
		this.timeOfStart = timeOfStart;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Exam(String name) {
		this.name = name;
	}
	
	public String add() throws IOException, SQLException {
		 Connection con = null;
		 PreparedStatement pstmt;
//	      Statement stmt = null;
//	      System.out.println("name=" + name + " timeOfstart=" + timeOfStart + " duration=" + duration);
//	      try {
//	         Class.forName("org.postgresql.Driver");
//	         c = DriverManager
//	            .getConnection("jdbc:postgresql://localhost:5432/exams",
//	            "postgres", "postgres");
//	         c.setAutoCommit(false);
//	         System.out.println("Opened database successfully");
//	         System.out.println("name=" + name + " duration=" + duration);
//	         stmt = c.createStatement();
//	         String sql = "INSERT INTO exams (name,duration) "
//	            + "VALUES (" + name + "," + duration + ");";
//	         stmt.executeUpdate(sql);
//
//	         stmt.close();
//	         c.commit();
//	         c.close();
//	      } catch (Exception e) {
//	    	  System.out.println("Error");
//	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//	         System.exit(0);
//	      }
//	      System.out.println("Records created successfully");
		 try {
			 con = ds.getConnection("postgres", "postgres");
			 pstmt = con.prepareStatement("INSERT INTO exams (name,duration)" + " VALUES (?, ?)");
			 pstmt.setString(1, name);
	         pstmt.setInt(2, duration);
	         
	         pstmt.execute();
	         
	         con.commit();
	         pstmt.close();
		 } finally {
			 if (con != null) con.close();
			 ds = null;
		 }
		 
	      return "examsList";
	   }
}
	

