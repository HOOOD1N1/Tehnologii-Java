package beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class Exam {
	
	private String name;
	private Date timeOfStart;
	private int duration;
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
	
	public String add() throws IOException {
		 Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/exams",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         System.out.println("name=" + name + " duration=" + duration);
	         stmt = c.createStatement();
	         String sql = "INSERT INTO exams (name,duration) "
	            + "VALUES (" + name + "," + duration + ");";
	         stmt.executeUpdate(sql);

	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	    	  System.out.println("Error");
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	      return "examsList";
	   }
}
	

