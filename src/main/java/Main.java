import java.io.IOException;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

		// necessary method calls! Do noe remove!
		DBConnection dbConnection = new DBConnection();
		dbConnection.setupCheck();

		DBHandler dbHandler = new DBHandler();
		dbHandler.dropTablesIfExists();
		dbHandler.createTables();

		InputHandler inputHandler = new InputHandler();
		inputHandler.addSubjectDataFromFile();
		inputHandler.addTeacherDataFromFile();

		//Printing methods
		System.out.println("");
		System.out.println("----------------- SUBJECTS ------------------");
		inputHandler.printAllSubjects();

		System.out.println("");
		System.out.println("----------------- TEACHERS ------------------");
		inputHandler.printAllTeachers();

		System.out.println("");
		System.out.println("----------- PRINTING ONE SUBJECT ------------");
		inputHandler.printSingeSubject();

		/*
		System.out.println("");
		System.out.println("------------- PRINTING ALL DATA -------------");
		inputHandler.printAllData();
		*/

	}
}
