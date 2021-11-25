package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the exams database table.
 * 
 */
@Entity
@Table(name="exams")
@NamedQuery(name="Exam.findAll", query="SELECT e FROM Exam e")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer duration;
	
	@Column(name="name")
	private String name;



	@Temporal(TemporalType.DATE)
	private Date starttime;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="exam")
	private List<Student> students;

	public Exam() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setExam(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setExam(null);

		return student;
	}

}