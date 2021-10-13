package model;

public class Injection {
	public String studentID;
	public String studentName;
	public int vaccineID;
	public String vaccineName;
	public String injectionID;
	public String firstPlace;
	public String firstDate;
	public String secondPlace;
	public String secondDate;

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

	public int getVaccineID() {
		return vaccineID;
	}

	public void setVaccineID(int vaccineID) {
		this.vaccineID = vaccineID;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getInjectionID() {
		return injectionID;
	}

	public void setInjectionID(String injectionID) {
		this.injectionID = injectionID;
	}

	public String getFirstPlace() {
		return firstPlace;
	}

	public void setFirstPlace(String firstPlace) {
		this.firstPlace = firstPlace;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getSecondPlace() {
		return secondPlace;
	}

	public void setSecondPlace(String secondPlace) {
		this.secondPlace = secondPlace;
	}

	public String getSecondDate() {
		return secondDate;
	}

	public void setSecondDate(String secondDate) {
		this.secondDate = secondDate;
	}

	public Injection(String studentID, String studentName, int vaccineID, String vaccineName, String injectionID,
			String firstPlace, String firstDate, String secondPlace, String secondDate) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.vaccineID = vaccineID;
		this.vaccineName = vaccineName;
		this.injectionID = injectionID;
		this.firstPlace = firstPlace;
		this.firstDate = firstDate;
		this.secondPlace = secondPlace;
		this.secondDate = secondDate;
	}

	public Injection() {
		super();
	}

	@Override
	public String toString() {
		return "Injection [studentID=" + studentID + ", studentName=" + studentName + ", vaccineID=" + vaccineID
				+ ", vaccineName=" + vaccineName + ", injectionID=" + injectionID + ", firstPlace=" + firstPlace
				+ ", firstDate=" + firstDate + ", secondPlace=" + secondPlace + ", secondDate=" + secondDate + "]";
	}

	public void printInjection() {
		System.out.format("| %-14s| %-19s| %-14d| %-15s| %-13s| %-13s| %-13s| %-13s| %-13s|", getStudentID(),
				getStudentName(), getVaccineID(), getVaccineName(), getInjectionID(), getFirstPlace(), getFirstDate(),
				getSecondPlace(), getSecondDate());
		System.out.println();
	}
}
