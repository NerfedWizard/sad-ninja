package application;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Must be able to create student and delete student verify info of user.....Do
 * we want the admin to handle the load of the credentials or should the
 * database handle the login credentials......
 * 
 * Maybe think about using the java.securtiy for random passwords for new users.
 */
public class Admin {
	private Databases db = new Databases();
	AtomicInteger userID = new AtomicInteger();

	public Admin() {

	}

	public void createStudent(String username, String name, String studentEmail, String password, Guardian guardian) {
		Student student = new Student(userID.getAndIncrement(), username, name, studentEmail, password, guardian);
		db.addStudent(student, userID);
	}

	public void createGuardian(String username, String name, String guardianEmail, String password, Student student) {
		Guardian guardian = new Guardian(userID.getAndIncrement(), username, name, guardianEmail, password, student);
		db.addGuardian(guardian, userID);

	}

	public void createTeacher(String username, String name, String facultyEmail, String password, int classID) {
		Teacher teacher = new Teacher(userID.getAndIncrement(), username, name, facultyEmail, password, classID);
		db.addTeacher(teacher, userID);
	}
}
