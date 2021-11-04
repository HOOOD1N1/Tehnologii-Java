package beans;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class ExamList {

	private List<Exam> listOfExams = null;
	private final String url = "jdbc:postgresql://localhost:5432/exams";
	private final String user = "postgres";
	private final String password = "postgres";

	public List<Exam> getListOfExams() {
		return listOfExams;
	}

	public void setListOfExams(List<Exam> listOfExams) {
		this.listOfExams = listOfExams;
	}

	public ExamList() {
		listOfExams = new ArrayList<Exam>();
		Connection conn = null;
		
		 Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         conn = DriverManager
	            .getConnection(url,
	            user, password);
	         
	         System.out.println("It works");
	         
	         stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * from exams;");
	         while(rs.next()) {
	        	 listOfExams.add(new Exam(rs.getString("name")));
	         }
	         stmt.close();
	         conn.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	      }
		
	}

	
	
}
