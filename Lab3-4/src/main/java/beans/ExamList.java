package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;


@ManagedBean(name="examList")
public class ExamList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Exam> listOfExams = null;

	@Resource(name="jdbc/exams")
	private DataSource ds1;

	public List<Exam> getListOfExams() throws SQLException {
		Connection conn = null;
		 PreparedStatement prStmt;
		 try {
			 conn = ds1.getConnection("postgres", "postgres");
			 prStmt = conn.prepareStatement("SELECT * from exams;");
			 ResultSet rs = prStmt.executeQuery();
			 while(rs.next()) {
	        	 listOfExams.add(new Exam(rs.getString("name")));
	         }
			 
			 rs.close();
			 
		 } finally {
			 if (conn != null) conn.close();
			 
		 }
		return listOfExams;
	}

	public void setListOfExams(List<Exam> listOfExams) {
		this.listOfExams = listOfExams;
	}

	public ExamList() {
		listOfExams = new ArrayList<Exam>();
	}
	
}
