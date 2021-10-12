package application;

/**
 * I think this method would be nice to store all the students and the teacher
 * associated with each class and the assignments and required materials for the
 * class
 */
public class ClassInformation {
	private int classID;
	private String classSubject;

	private Teacher teacher;

	public ClassInformation(int classID, String classSubject, Teacher teacher) {
		setClassID(classID);
		setClassSubject(classSubject);
		setTeacher(teacher);
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public String getClassSubject() {
		return classSubject;
	}

	public void setClassSubject(String classSubject) {
		this.classSubject = classSubject;
	}

}
