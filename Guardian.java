package application;

import java.util.ArrayList;

public class Guardian {
	private int id = 0;
	private String username;
	private String name;
	private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	private ArrayList<String> classes = new ArrayList<String>();
	private String guardianEmail;
	private String password;
	private Student student;

	public Guardian(int id, String username, String name, String guardianEmail, String password, Student student) {
		this.setId(id);
		this.setUsername(username);
		this.setName(name);
		this.setFacultyEmail(guardianEmail);
		this.setPassword(password);
		this.setStudent(student);

	}
	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}
//	public void addGuardian(Guardian guardian) {
//		this.guardian = guardian;
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
		return guardianEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.guardianEmail = facultyEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Teacher> getTeachers() {
		return this.teachers;
	}

//	public void setTeachers(ArrayList<Student> students) {
//		this.teachers = students;
//	}

	public ArrayList<String> getClasses() {
		return classes;
	}

	public void addClasses(String classTaken) {
		this.classes.add(classTaken);
	}
//	public ArrayList<String> getClasses() {
//		return classes;
//	}
//	public void setClasses(ArrayList<String> classes) {
//		this.classes = classes;
//	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		
	}

}
