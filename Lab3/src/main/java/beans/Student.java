package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Student {

	private String name;
	private List<String> exams = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getExams() {
		return exams;
	}
	public void setExams(List<String> exams) {
		this.exams = exams;
	}
	public Student(String name) {
		this.name = name;
	}
	
}
