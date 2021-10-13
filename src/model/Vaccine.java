package model;

public class Vaccine {
	public int vaccineID;
	public String vaccineName;

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

	public Vaccine(int vaccineID, String vaccineName) {
		super();
		this.vaccineID = vaccineID;
		this.vaccineName = vaccineName;
	}

	public Vaccine() {
		super();
	}

	@Override
	public String toString() {
		return "Vaccine [vaccineID=" + vaccineID + ", vaccineName=" + vaccineName + "]";
	}

	public void printVaccine() {
		 System.out.printf("| %-15s| %-25s|\n", vaccineID, vaccineName);
	}
}
