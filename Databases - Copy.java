package application;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Databases {
	private ArrayList<String> userLogin = new ArrayList<String>();
	private ArrayList<Student> studentArchive = new ArrayList<Student>();
	private ArrayList<Guardian> guardianArchive = new ArrayList<Guardian>();
	private ArrayList<Teacher> teacherArchive = new ArrayList<Teacher>();
	private ArrayList<ClassInformation> classArchive = new ArrayList<ClassInformation>();

	public Databases() {

	}

	public void addStudent(Student student, AtomicInteger userID) {
		this.studentArchive.add(userID.get(),student);
	}

	public void addGuardian(Guardian guardian, AtomicInteger userID) {
		this.guardianArchive.add(userID.get(), guardian);
	}

	public void addTeacher(Teacher teacher, AtomicInteger userID) {
		this.teacherArchive.add(userID.get(),teacher);
	}
	public Student getStudentArchive(AtomicInteger userID) {
		return studentArchive.get(userID.get());
	}
	public Guardian getGuardianArchive(AtomicInteger userID) {
		return guardianArchive.get(userID.get());
	}
	public Teacher getTeacherArchive(AtomicInteger userID) {
		return teacherArchive.get(userID.get());
	}

	public ArrayList<ClassInformation> getClassArchive() {
		return classArchive;
	}

	public void setClassInfo(ArrayList<ClassInformation> classArchive) {
		this.classArchive = classArchive;
	}

	public void setUserLogin(ArrayList<String> userLogin) {
		this.userLogin = userLogin;
	}
	public ArrayList<String> userLogin(){
		return userLogin;
	}

}
