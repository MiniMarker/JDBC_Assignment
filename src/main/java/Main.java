import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

		UserMenu userMenu = new UserMenu();


		/*
		// necessary method calls! Do noe remove!
		DBConnection dbCon = new DBConnection();
		dbCon.setupCheck();

		DBHandler dbHandler = new DBHandler();
		dbHandler.dropTablesIfExists(dbCon.connect());
		dbHandler.createTables(dbCon.connect());

		InputHandler inputHandler = new InputHandler();
		inputHandler.addSubjectDataFromFile(dbCon.connect());
		inputHandler.addTeacherDataFromFile(dbCon.connect());

		//Printing methods
		System.out.println("");
		System.out.println("----------------- SUBJECTS ------------------");
		inputHandler.printAllSubjects(dbCon.connect());

		System.out.println("To print one subject you have to choose between");

		System.out.println("");
		System.out.println("----------- PRINTING ONE SUBJECT ------------");
		Scanner input = new Scanner(System.in);
		String code = input.nextLine();
		inputHandler.printSingleSubject(dbCon.connect(), code);

		System.out.println("");
		System.out.println("----------------- TEACHERS ------------------");
		inputHandler.printAllTeachers(dbCon.connect());




		System.out.println("");
		System.out.println("------------- PRINTING ALL DATA -------------");
		inputHandler.printAllData();
		*/

	}
}
