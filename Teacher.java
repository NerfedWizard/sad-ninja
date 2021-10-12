package application;

import java.util.ArrayList;

public class Teacher {
	int id = 0;
	private String username;
	private String name;
	private ArrayList<Student> students = new ArrayList<Student>();
//	private ArrayList<Guardian> guardians = new ArrayList<Guardian>();
	private int classID;
	private String facultyEmail;
	private String password;
	private String assignmentName;
	private int assignID;

	public Teacher(int id, String username, String name, String facultyEmail, String password, int classID) {
		this.setId(id);
		this.setUsername(username);
		this.setName(name);
		this.setFacultyEmail(facultyEmail);
		this.setPassword(password);
//		.setSchoolClass(classID);

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.id+=1;
	}
	public void addStudent(Student student) {
		this.students.add(student);
	}
//	public void addGuardian(Guardian guardian) {
//		this.guardians.add(guardian);
//	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFacultyEmail() {
		return facultyEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.facultyEmail = facultyEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

//	public ArrayList<Guardian> getGuardians() {
//		return guardians;
//	}

public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public int getAssignID() {
		return assignID;
	}
	public void setAssignID(int assignID) {
		this.assignID = assignID;
	}

}
