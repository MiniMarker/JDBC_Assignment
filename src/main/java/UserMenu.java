import java.util.Scanner;

public class UserMenu {
	private DBHandler dbHandler;
	private Scanner clientInput = new Scanner(System.in);
	private String choice;
	private boolean flag = true;

	private InputHandler inputHandler;

	public UserMenu() {

		while (flag) {
			startMenu();
		}
	}

	/**
	 * Welcome text and initialization of the database.
	 * All of the menus is written in norwegian
	 */
	private void startMenu() {

		System.out.println("Velkommen til serveren! \n" +
				"Skriv inn ønsket valg og avslutt med enter\n\n" +
				"1: Koble til og opprett databasen\n" +
				"0: Avslutt");

		choice = clientInput.nextLine();

		switch (choice) {
			case "1":
				DBConnection dbConnection = new DBConnection();
				dbConnection.setupCheck();

				//neste meny
				serverInitMenu();
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
	 * database table creation/deletion
	 */
	private void serverInitMenu() {
		System.out.println("1: Drop tabellen 'subject' hvis den eksisterer \n" +
				"2: Opprett tabeller \n" +
				"9: Tilbake \n" +
				"0: Avslutt");

		choice = clientInput.nextLine();

		switch (choice) {
			case "1":
				dbHandler = new DBHandler();
				dbHandler.dropTablesIfExists();
				System.out.print("Dropper tabellen hvis den eksisterer...");
				System.out.println("Vellykket \n");

				//rekursivt kall til menyen fordi
				serverInitMenu();
				break;

			case "2":

				System.out.println("Oppretter tabeller...");
				dbHandler.createTables();
				System.out.println("Vellykket! \n");

				//neste meny
				insertDataToTables();
				break;

			case "9":
				System.out.println("Går tilbake... \n");
				startMenu();
				break;

			case "0":
				System.out.println("Avslutter...");
				flag = false;
				System.out.println("Vellykket");
				break;

			default:
				System.out.println("Feil svar! Prøv igjen... \n");

				//rekursivt kall hvis ikke klienten svarer som forventet
				serverInitMenu();
				break;
		}
	}

	/**
	 * populate the tables
	 */
	private void insertDataToTables() {
		System.out.println("1: Fyll inn tabellen med data fra fil \n" +
				"9: Tilbake \n" +
				"0: Avslutt");

		choice = clientInput.nextLine();

		switch (choice) {
			case "1":
				System.out.println("Oppretter 'subject' table...'");
				inputHandler = new InputHandler();
				System.out.println(inputHandler.addSubjectDataFromFile());
				System.out.println("Vellykket");

				//neste meny
				printDataFromDatabaseMenu();
				break;

			case "9":
				System.out.println("Går tilbake... \n");
				serverInitMenu();
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
		System.out.println("1: Print data fra tabellen 'subject'\n" +
				"2: Print ett subject fra emnekode\n" +
				"1: Print data fra tabellen 'teacher'" +
				"9: Tilbake\n" +
				"0: Avslutt");

		choice = clientInput.nextLine();

		switch (choice) {
			case "1":
				System.out.println("----------------- SUBJECTS ------------------");
				inputHandler = new InputHandler();
				System.out.println(inputHandler.printAllSubjects());

				//slutten av menyen, rekursivt kall
				printDataFromDatabaseMenu();
				break;

			case "2":
				System.out.println("Skriv inn emnekode:");
				String emnekode = clientInput.nextLine();
				System.out.println("----------- PRINTING ONE SUBJECT ------------");
				inputHandler = new InputHandler();
				System.out.println(inputHandler.printSingleSubject(emnekode) + "\n");

				//slutten av menyen, rekursivt kall
				printDataFromDatabaseMenu();
				break;

			case "3":
				System.out.println("----------------- TEACHERS ------------------");
				inputHandler = new InputHandler();
				System.out.println(inputHandler.printAllTeachers());

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

