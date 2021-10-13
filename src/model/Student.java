package model;

public class Student {
	public String studentID;
	public String studentName;

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Student(String studentID, String studentName) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", studentName=" + studentName + "]";
	}
	
	public void printStudent() {
		System.out.printf("| %-15s| %-25s|\n",studentID, studentName);
	}
}
