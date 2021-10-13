package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import model.Address;
import model.Injection;
import model.Student;
import model.Vaccine;

public class ControllerVaccine implements Overview {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Student> listSV = new ArrayList();
	public static ArrayList<Vaccine> listVaccine = new ArrayList();
	public static ArrayList<Address> listAddress = new ArrayList();
	public static ArrayList<Injection> listInj = new ArrayList<Injection>();
	Injection inj = new Injection();

	public boolean checkTrung(String a) {
		for (int i = 0; i < listInj.size(); i++) {
			if (listInj.get(i).getInjectionID().toUpperCase().equals(a.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkIdStudentExist(String a) {
		for (int i = 0; i < listSV.size(); i++) {
			if (listSV.get(i).getStudentID().trim().equalsIgnoreCase(a.trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkIdHadInjected(String a) {
		for (int i = 0; i < listInj.size(); i++) {
			if (listInj.get(i).getStudentID().trim().equalsIgnoreCase(a.trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkIdVaccine(int a) {
		for (int i = 0; i < listVaccine.size(); i++) {
			if (listVaccine.get(i).getVaccineID() == a) {
				return true;
			}
		}
		return false;
	}

	public boolean checkPlace(String a) {
		for (int i = 0; i < listAddress.size(); i++) {
			if (listAddress.get(i).getAddress().toUpperCase().trim().equals(a.trim().toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkDate(String date) {
		return date.matches(
				"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
	}

	public boolean checkIdInjExist(String a) {
		for (int i = 0; i < listInj.size(); i++) {
			if (listInj.get(i).getInjectionID().toUpperCase().equals(a.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public int CompareTwoDatesTest(String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		try {
			d1 = sdformat.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sdformat.parse(dtf.format(now));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		if (d1.compareTo(d2) > 0) {
			return 1; // còn hạn sử dụng
		} else if (d1.compareTo(d2) < 0) {
			return -1; // đã hết hạn
		} else if (d1.compareTo(d2) == 0) {
			return 2; // hạn hôm nay
		}
		return 0;
	}

	public long checkTwoDate(String str1, String str2) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null, date2 = null;
		try {
			date1 = df.parse(str1);
			date2 = df.parse(str2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long tempDay = date2.getTime() - date1.getTime();
		long tempDayDiff = tempDay / (24 * 60 * 60 * 1000);
		long getWeek = tempDayDiff / 7;
		return getWeek;
	}

	@Override
	public void add() {
		while (true) {

			// ID injection
			System.out.print("Enter id injection [INJ...]: ");
			String id = sc.nextLine().toUpperCase();
			while (checkTrung(id.toUpperCase())) {
				System.err.println("ID duplicated !");
				System.out.print("Enter id injection [INJ...]: ");
				id = sc.nextLine().toUpperCase();
			}
			inj.setInjectionID(id);

			// ID Student
			showStudent();
			System.out.print("Enter ID Student [SE......]:");
			String ids = sc.nextLine().toUpperCase();
			while (!checkIdStudentExist(ids)) {
				System.err.println("ID student not exist !");
				System.out.print("Enter ID Student [SE......]:");
				ids = sc.nextLine().toUpperCase();
			}
			while (checkIdHadInjected(ids)) {
				System.err.println("This student had injected !");
				System.out.print("Enter ID Student [SE......]:");
				ids = sc.nextLine().toUpperCase();
			}
			String nameStudent = null;
			for (int i = 0; i < listSV.size(); i++) {
				if (listSV.get(i).getStudentID().equals(ids)) {
					nameStudent = listSV.get(i).getStudentName();
				}
			}
			inj.setStudentName(nameStudent);
			// Student name auto
			inj.setStudentID(ids.toUpperCase());

			// ID vaccine
			showVaccine();
			System.out.print("Enter ID vaccine: ");
			int idv = Integer.parseInt(sc.nextLine());
			while (!checkIdVaccine(idv)) {
				System.err.println("Id vaccine not exist !");
				System.out.print("Enter ID vaccine: ");
				idv = Integer.parseInt(sc.nextLine());
			}
			inj.setVaccineID(idv);
			// name vaccine
			String nameVaccine = null;
			for (int i = 0; i < listVaccine.size(); i++) {
				if (listVaccine.get(i).getVaccineID() == idv) {
					nameVaccine = listVaccine.get(i).getVaccineName();
				}
			}
			inj.setVaccineName(nameVaccine);

			// First place
			System.out.print("Enter the first place: ");
			String fp = sc.nextLine();
			while (!checkPlace(fp)) {
				System.err.println("Please enter the name of a province !");
				System.out.print("Enter the first place: ");
				fp = sc.nextLine();
			}
			inj.setFirstPlace(fp.toUpperCase());

			// First date
			System.out.print("Enter the first date [dd/mm/yyyy]: ");
			String date1 = sc.nextLine();
			while (date1.isEmpty()) {
				System.err.println("No blank !");
				System.out.print("Enter the first date [dd/mm/yyyy]: ");
				date1 = sc.nextLine();
			}
			while (!checkDate(date1)) {
				System.err.println("Date isn't format !");
				System.out.print("Enter the first date [dd/mm/yyyy]: ");
				date1 = sc.nextLine();
			}
			inj.setFirstDate(date1);

			listInj.add(inj);
			System.err.println("Added successfull !");

			System.out.print("Do you want to continue adding ? (Y/N) ");
			String b = sc.nextLine();
			if (b.equalsIgnoreCase("n")) {
				return;
			}
		}
	}

	@Override
	public void update() {
		System.out.print("Enter ID injection to update: ");
		String up_inj = sc.nextLine().toUpperCase();
		while (up_inj.isEmpty()) {
			System.err.println("No blank !");
			System.out.print("Enter ID injection to update: ");
			up_inj = sc.nextLine().toUpperCase();
		}
		while (!checkIdInjExist(up_inj)) {
			System.err.println("ID injection not exist !");
			System.out.print("Enter ID injection to update: ");
			up_inj = sc.nextLine().toUpperCase();
		}
		for (Injection updateInj : listInj) {
			if (updateInj.getInjectionID().equals(up_inj)) {
				System.out.print("Enter second place: ");
				String sp = sc.nextLine().toUpperCase();
				while (!checkPlace(sp)) {
					System.err.println("Please enter the name of a province !");
					System.out.println("Enter second place: ");
					sp = sc.nextLine().toUpperCase();
				}
				updateInj.setSecondPlace(sp);

				// check date 2
				System.out.println(
						"Enter the second date(Must be given 4 to 12 weeks after the first injection) [dd/mm/yyyy]: ");
				String date2 = sc.nextLine();
				while (!checkDate(date2)) {
					System.err.println("Date isn't format !");
					System.out.print("Enter the second date [dd/mm/yyyy]: ");
					date2 = sc.nextLine();
				}
				while (checkTwoDate(updateInj.getFirstDate(), date2) < 4
						|| checkTwoDate(updateInj.getFirstDate(), date2) > 12) {
					System.err.println("Second injection time from 4 to 12 weeks !");
					System.out.print("Enter the second date [dd/mm/yyyy]: ");
					date2 = sc.nextLine();
					while (!checkDate(date2)) {
						System.err.println("Date isn't format !");
						System.out.print("Enter the second date [dd/mm/yyyy]: ");
						date2 = sc.nextLine();
					}
				}
				updateInj.setSecondDate(date2);
				System.err.println("Update injection successful !");
			}
		}
	}

	@Override
	public void remove() {
		boolean flag2 = false;
		System.out.print("Enter ID injection to delete: ");
		String id_delete = sc.nextLine();
		while (id_delete.isEmpty()) {
			System.err.println("No blank !");
			System.out.println("Enter ID injection to delete: ");
			id_delete = sc.nextLine();
		}
		System.out.print("Are you sure delete this injection? (Y/N) ");
		String b = sc.nextLine();
		if (b.equalsIgnoreCase("n")) {
			System.err.println("Delete fail !");
			return;
		}
		for (int i = 0; i < listInj.size(); i++) {
			if (listInj.get(i).getInjectionID().toUpperCase().equals(id_delete.toUpperCase())) {
				listInj.remove(i);
				System.err.println("Deleted successful !");
				flag2 = true;
			}
		}
		if (!flag2) {
			System.err.println("This ID injection not exist !");
		}
	}

	@Override
	public void search() {
		while (true) {
			boolean flag = false;
			System.out.print("Enter the student ID to search: ");
			String idst_search = sc.nextLine();
			for (int i = 0; i < listInj.size(); i++) {
				if (listInj.get(i).getStudentID().equals(idst_search.toUpperCase())) {
					if (!flag) {
						System.out.format("%-15s", "| Student ID");
						System.out.format("%-22s", " | Student name");
						System.out.format("%-15s", "| Vaccine ID ");
						System.out.format("%-18s", " | Vaccine name");
						System.out.format("%-15s", "| Injection ID");
						System.out.format("%-15s", "| First place");
						System.out.format("%-15s", "| First date");
						System.out.format("%-15s", "| Second place");
						System.out.format("%-15s", "| Second date  |");
						System.out.println();
						System.out.println(
								"=================================================================================================================================================");
						listInj.get(i).printInjection();
						flag = true;
					}
				}
			}
			if (!flag) {
				System.err.println("ID student not exist !");
			}
			System.out.print("Do you want to continue searching? (Y/N) ");
			String b = sc.nextLine();
			if (b.equalsIgnoreCase("n")) {
				return;
			}
		}
	}

	public void showInjecton() {
		System.out.format("%-15s", "| Student ID");
		System.out.format("%-22s", " | Student name");
		System.out.format("%-15s", "| Vaccine ID ");
		System.out.format("%-18s", " | Vaccine name");
		System.out.format("%-15s", "| Injection ID");
		System.out.format("%-15s", "| First place");
		System.out.format("%-15s", "| First date");
		System.out.format("%-15s", "| Second place");
		System.out.format("%-15s", "| Second date  |");
		System.out.println();
		System.out.println(
				"================================================================================================================================================");
		for (int i = 0; i < listInj.size(); i++) {
			listInj.get(i).printInjection();
		}
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public static ArrayList<Injection> readInjection(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bfr = new BufferedReader(isr);
			String str = bfr.readLine();
			while (str != null) {
				Injection i = new Injection();
				String arr[] = str.split(";");
				if (arr.length == 9) {
					i.setStudentID(arr[0].toUpperCase());
					i.setStudentName(arr[1]);
					i.setVaccineID(Integer.parseInt(arr[2]));
					i.setVaccineName(arr[3]);
					i.setInjectionID(arr[4].toUpperCase());
					i.setFirstPlace(arr[5].toUpperCase());
					i.setFirstDate(arr[6].toLowerCase());
					i.setSecondPlace(arr[7].toUpperCase());
					i.setSecondDate(arr[8].toLowerCase());
					listInj.add(i);
				}
				str = bfr.readLine();
			}
			fis.close();
			isr.close();
			bfr.close();
			return listInj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void showAddress() {
		for (int i = 0; i < listAddress.size(); i++) {
			listAddress.get(i).printAddress();
		}
	}

	public static ArrayList<Address> readAddres(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bfr = new BufferedReader(isr);
			String str = bfr.readLine();
			while (str != null) {
				Address a = new Address();
				String arr[] = str.split(";");
				if (arr.length == 2) {
					a.setNumAddres(arr[0]);
					a.setAddress(arr[1]);
					listAddress.add(a);
				}
				str = bfr.readLine();
			}
			fis.close();
			isr.close();
			bfr.close();
			return listAddress;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void showVaccine() {
		System.out.format("%-15s", "|   Vaccine ID");
		System.out.format("%-25s", "  |    Vaccine name");
		System.out.println();
		System.out.println("============================================");
		for (int i = 0; i < listVaccine.size(); i++) {
			listVaccine.get(i).printVaccine();
		}
	}

	public static ArrayList<Vaccine> readVaccine(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bfr = new BufferedReader(isr);
			String str = bfr.readLine();
			while (str != null) {
				String arr[] = str.split(";");
				if (arr.length == 2) {
					Vaccine v = new Vaccine();
					v.setVaccineID(Integer.parseInt(arr[0]));
					v.setVaccineName(arr[1]);
					listVaccine.add(v);
				}
				str = bfr.readLine();
			}
			fis.close();
			isr.close();
			bfr.close();
			return listVaccine;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void showStudent() {
		System.out.format("%-15s", "|   Student ID");
		System.out.format("%-25s", "  |    Student name");
		System.out.println();
		System.out.println("============================================");
		for (int i = 0; i < listSV.size(); i++) {
			listSV.get(i).printStudent();
		}
	}

	public static ArrayList<Student> readStudent(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bfr = new BufferedReader(isr);
			String str = bfr.readLine();
			while (str != null) {
				String arr[] = str.split(";");
				if (arr.length == 2) {
					Student a = new Student();
					a.setStudentID(arr[0].toUpperCase());
					a.setStudentName(arr[1]);
					listSV.add(a);
				}
				str = bfr.readLine();
			}
			fis.close();
			isr.close();
			bfr.close();
			return listSV;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean saveInjection(ArrayList<Injection> i) {
		try {
			FileOutputStream fos = new FileOutputStream("injection.txt");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bfw = new BufferedWriter(osw);
			for (Injection inj : i) {
				String str = inj.getStudentID() + ";" + inj.getStudentName() + ";" + inj.getVaccineID() + ";"
						+ inj.getVaccineName() + ";" + inj.getInjectionID() + ";" + inj.getFirstPlace() + ";"
						+ inj.getFirstDate() + ";" + inj.getSecondPlace() + ";" + inj.getSecondDate();
				bfw.write(str);
				bfw.newLine();
			}

			bfw.close();
			osw.close();
			fos.close();
			System.err.println("Save file successfull !");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
