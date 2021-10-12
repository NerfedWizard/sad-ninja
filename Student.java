package application;

import java.util.ArrayList;

/***/
public class Student {
	private int id = 0;
	private String username;
	private String fullName;
	private ArrayList<ClassInformation> classes = new ArrayList<ClassInformation>();
	private String studentEmail;
	private String password;
	private Guardian guardian;

	public Student(int id, String username, String name, String studentEmail, String password, Guardian guardian) {
		this.id = id;
		this.setUsername(username);
		this.setFullName(name);
		this.setStudentEmail(studentEmail);
		this.setPassword(password);
		this.setGuardian(guardian);

	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public String getStudentEmail() {
		return this.studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public ArrayList<ClassInformation> getClasses() {
		return this.classes;
	}

	public void addClasses(int classID, String classSubject, Teacher teacher) {
		ClassInformation classTaken = new ClassInformation(classID, classSubject, teacher);
		this.classes.add(classTaken);
	}


	public Guardian getGuardian() {
		return this.guardian;
	}

	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}

	public int getId() {
		return this.id;
	}

}
