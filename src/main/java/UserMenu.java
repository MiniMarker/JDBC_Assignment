import java.util.Scanner;

public class UserMenu {
	private Scanner clientInput = new Scanner(System.in);
	private String choice;
	private boolean flag = true;

	private InputHandler inputHandler;
	private DBConnection dbCon = new DBConnection();

	public UserMenu() {

		while (flag) {
			startMenu();
		}
	}

	public static void main(String[] args) {
		new UserMenu();
	}

	/**
	 * Welcome text and initialization of the database.
	 * All of the menus is written in norwegian
	 */
	private void startMenu() {

		System.out.println(
						"** Skriv inn ønsket valg og avslutt med enter **\n\n" +
						"------------------------------------------------------------------------------------------------- \n" +
						"1: Koble til og opprett databasene\n" +
						"0: Avslutt\n" +
						"-------------------------------------------------------------------------------------------------");

		choice = clientInput.nextLine();

		switch (choice) {
			case "1":
				DBHandler dbHandler = new DBHandler();

				System.out.print("\nKobler til og kjører en setUpCheck...");
				dbCon.setupCheck();
				System.out.println("Vellykket");

				System.out.print("Dropper tabellen hvis den eksisterer...");
				dbHandler.dropTablesIfExists(dbCon.connect());
				System.out.println("Vellykket");

				System.out.println("Oppretter tabeller...");
				dbHandler.createTables(dbCon.connect());
				System.out.println("Vellykket \n");

				//neste meny
				insertDataToTables();
				break;

			case "0":
				System.out.print("Avslutter...");
				flag = false;
				System.out.println("Vellykket");
				break;

			default:
				System.out.println("Feil svar! Prøv igjen... \n");

				//rekursivt kall hvis ikke klienten svarer som forventet
				startMenu();
				break;
		}

	}


	/**
	 * populate the tables
	 */
	private void insertDataToTables() {
		System.out.println(
				"-------------------------------------------------------------------------------------------------\n" +
				"1: Fyll inn tabellene med data fra filene \n" +
				"9: Tilbake \n" +
				"0: Avslutt\n" +
				"-------------------------------------------------------------------------------------------------");

		choice = clientInput.nextLine();

		switch (choice) {
			case "1":
				System.out.println("\nFyller tabellen 'subject' med data...'");
				inputHandler = new InputHandler();
				inputHandler.addSubjectDataFromFile(dbCon.connect());
				System.out.println("Vellykket");

				System.out.println("Fyller tabellen 'teacher' med data...'");
				inputHandler = new InputHandler();
				inputHandler.addTeacherDataFromFile(dbCon.connect());
				System.out.println("Vellykket\n");

				//neste meny
				printDataFromDatabaseMenu();
				break;

			case "9":
				System.out.println("Går tilbake... \n");
				startMenu();
				break;

			case "0":
				System.out.print("Avslutter...");
				flag = false;
				System.out.println("Vellykket");
				break;

			default:
				System.out.println("Feil svar! Prøv igjen... \n");

				//rekursivt kall hvis ikke klienten svarer som forventet
				insertDataToTables();
				break;
		}
	}

	/**
	 * print data from the table, either by entering a specific subject code or print all the rows in the table.
	 */
	private void printDataFromDatabaseMenu() {
		System.out.println(
				"-------------------------------------------------------------------------------------------------\n" +
						"1: Print all data fra tabellen 'subject'\n" +
						"2: Print ett subject spesifisert ved emnekode\n" +
						"3: Print all data fra tabellen 'teacher'\n" +
						"4: Print all data i alle tabellene i databasen\n" +
						"9: Tilbake\n" +
						"0: Avslutt\n" +
						"-------------------------------------------------------------------------------------------------");

		choice = clientInput.nextLine();

		switch (choice) {
			case "1":
				System.out.println("\n------------------------------------------- SUBJECTS --------------------------------------------");
				inputHandler = new InputHandler();
				System.out.println(inputHandler.printAllSubjects(dbCon.connect()));

				//slutten av menyen, rekursivt kall
				printDataFromDatabaseMenu();
				break;

			case "2":
				System.out.print("Skriv inn emnekode: ");
				String emnekode = clientInput.nextLine();
				System.out.println("\n--------------------------------------- PRINTING " + emnekode.toUpperCase() + " ---------------------------------------");
				inputHandler = new InputHandler();
				System.out.println(inputHandler.printSingleSubject(dbCon.connect(), emnekode) + "\n");

				//slutten av menyen, rekursivt kall
				printDataFromDatabaseMenu();
				break;

			case "3":
				System.out.println("\n------------------------------------------- TEACHERS --------------------------------------------");
				inputHandler = new InputHandler();
				System.out.println(inputHandler.printAllTeachers(dbCon.connect()));

				//slutten av menyen, rekursivt kall
				printDataFromDatabaseMenu();
				break;

			case "4":
				System.out.println("\n------------------------------------------- ALL DATA --------------------------------------------");
				inputHandler = new InputHandler();
				inputHandler.printAllData(dbCon.connect());

				//slutten av menyen, rekursivt kall
				printDataFromDatabaseMenu();
				break;

			case "9":
				System.out.println("Går tilbake...");
				System.out.println("");

				insertDataToTables();
				break;

			case "0":
				System.out.print("Avslutter...");
				flag = false;
				System.out.println("Vellykket");
				break;

			default:
				System.out.println("Feil svar! Prøv igjen...\n");

				//rekursivt kall hvis ikke klienten svarer som forventet
				printDataFromDatabaseMenu();
				break;
		}
	}
}

