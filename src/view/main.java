package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ControllerVaccine;

public class main {
	static Scanner sc = new Scanner(System.in);
	static ControllerVaccine ctv = new ControllerVaccine();

	public static void menu() {
		System.out.println("\n====================== MENU =============================");
		System.out.println("|1. Show information all students have been injected    |");
		System.out.println("|2. Add student's vaccine injection information         |");
		System.out.println("|3. Updating information of students' vaccine injection |");
		System.out.println("|4. Delete student vaccine injection information        |");
		System.out.println("|5. Search for injection information by studentID       |");
		System.out.println("|6. Save file                                           |");
		System.out.println("|7. Quit                                                |");
		System.out.println("=========================================================");
	}

	public static void main(String[] args) {
		ctv.listSV = ctv.readStudent("student.txt");
		ctv.listVaccine = ctv.readVaccine("vaccine.txt");
		ctv.listAddress = ctv.readAddres("tinh.txt");
		ctv.listInj = ctv.readInjection("injection.txt");

		while (true) {
			System.out.println("Welcome to Food Management - @2021 by <SE150627 - Tran Thien Quoc Anh>");
			int choice = 0;
			while (true) {
				menu();
				try {
					System.out.print("Enter your choice: ");
					choice = Integer.parseInt(sc.nextLine());
					switch (choice) {
					case 1:
						ctv.showInjecton();
						break;
					case 2:
						ctv.add();
						break;
					case 3:
						ctv.update();
						break;
					case 4:
						ctv.remove();
						break;
					case 5:
						ctv.search();
						break;
					case 6: 
						ctv.saveInjection(ctv.listInj);
						break;
					case 7:
						System.err.println("GoodBye !");
						System.exit(0);
						break;
					default:
						System.err.println("Your choice not valid !");
						break;
					}
				} catch (NumberFormatException e) {
					System.err.println("Not valid !");
				}
			}
		}
	}

}
