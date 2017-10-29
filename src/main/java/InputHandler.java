import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class InputHandler  {

	private Scanner scanner = new Scanner(System.in);
	private DBConnection dbCon;
	private DBHandler dbHan;
	private BufferedReader br;
	private String text;
	private StringBuilder stringBuilder;
	private String result;

	private String line;
	private String splitBy = ",";

	public InputHandler() {
		dbCon = new DBConnection();
		dbHan = new DBHandler();

		dbCon.connect();
	}

	/**
	 * Add data to Subject table form file
	 * @return returns numbers of rows created
	 */
	public String addSubjectDataFromFile() {

		try (Connection con = dbCon.ds.getConnection()) {

			try (BufferedReader br = new BufferedReader(new FileReader("target/textfiles/subjects.csv"));
			     PreparedStatement prepSubjectStmt = con.prepareStatement("INSERT INTO Subject VALUES (?,?,?,?)")) {

				int count = 0;

				while ((line = br.readLine()) != null) {

					// Split by comma (,)
					String[] subjects = line.split(splitBy);

					prepSubjectStmt.setString(1, subjects[0]);
					prepSubjectStmt.setString(2, subjects[1]);
					prepSubjectStmt.setDouble(3, Double.parseDouble(subjects[2]));
					prepSubjectStmt.setInt(4, Integer.parseInt(subjects[3]));

					prepSubjectStmt.executeUpdate();
					count++;
				}

				text = (count + " Rows created in table 'Subject'");

			} catch (SQLException sqle) {
				System.out.println("SQL ERROR! " + sqle.getMessage());
			} catch (IOException ioex) {
				System.out.println("IO ERROR! " + ioex.getMessage());
			}
		} catch (SQLException sqle2) {
			System.out.println("SQL ERROR! " + sqle2.getMessage());
		}
		return text;
	}

	/**
	 * Add data to Teacher table form file
	 */

	public void addTeacherDataFromFile(){

		try (Connection con = dbCon.ds.getConnection()){

			try (BufferedReader br = new BufferedReader(new FileReader("target/textfiles/teachers.csv"));
			     PreparedStatement prepTeacherStmt = con.prepareStatement("INSERT INTO Teacher VALUES (?,?,?,?)")) {

				int count = 0;

				while ((line = br.readLine()) != null){

					// Split by comma (,)
					String[] teachers = line.split(splitBy);

					prepTeacherStmt.setInt(1, Integer.parseInt(teachers[0]));
					prepTeacherStmt.setString(2, teachers[1]);
					prepTeacherStmt.setString(3, teachers[2]);
					prepTeacherStmt.setString(4, teachers[3]);

					prepTeacherStmt.executeUpdate();
					count++;
				}

				System.out.println(count + " Rows created in table 'Teacher'");

			} catch (SQLException sqle){
				System.out.println("SQL ERROR! " + sqle.getMessage());
			} catch (IOException ioex){
				System.out.println("IO ERROR! " + ioex.getMessage());
			}
		} catch (SQLException sqle2){
			System.out.println("SQL ERROR! " + sqle2.getMessage());
		}
	}

	/**
	 * Print one subject defined by subject.code
	 * @return one ResultSet defined by a query based on subject.code
	 */
	public String printSingleSubject(String code) {
		try (Connection con = dbCon.ds.getConnection();
		     PreparedStatement prepSingeSubjectStmt = con.prepareStatement("SELECT * FROM Subject WHERE code = ?" + "\n")) {

			prepSingeSubjectStmt.setString(1, code);

			ResultSet rs = prepSingeSubjectStmt.executeQuery();

			while (rs.next()) {
				text = (rs.getString(1) + " "
						+ rs.getString(2) + " "
						+ rs.getDouble(3) + " "
						+ rs.getInt(4));
			}

		} catch (SQLException sqle) {
			System.out.println("SQL ERROR! " + sqle.getMessage());
		}
		return text;
	}

	/**
	 * Print all subjects in database
	 * @return Strings of all rows in the db-table built by using a StringBuilder
	 */
	public String printAllSubjects() {

		try (Connection con = dbCon.ds.getConnection();
		     Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM Subject");

			stringBuilder = new StringBuilder();

			while (rs.next()) {
				stringBuilder.append(
						"Emnekode: " + rs.getString(1) +
								" Enmenavn: " + rs.getString(2) +
								" Varighet: " + rs.getDouble(3) +
								" Antall påmeldte: " + rs.getInt(4) + "\n");
			}

			result = stringBuilder.toString();

		} catch (SQLException sqle) {
			System.out.println("SQL ERROR! " + sqle.getMessage());
		}
		return result;
	}

	/**
	 * Print all teachers in database
	 */
	public String printAllTeachers() {

		try (Connection con = dbCon.ds.getConnection();
		     Statement stmt = con.createStatement()) {

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Teacher");

			stringBuilder = new StringBuilder();

			while (rs.next()){
				stringBuilder.append("Lærerid: " + rs.getInt(1) +
						" Navn: " + rs.getString(2) +
						" Ikke ledig: " + rs.getString(3) +
						" Kontaktinfo: " + rs.getString(4));
			}

			result = stringBuilder.toString();

		} catch (SQLException sqle){
			System.out.println("SQL ERROR! " + sqle.getMessage());
		}
		return result;
	}

	/**
	 * Print all data from tables
	 */

	public void printAllData(){
		System.out.println(printAllSubjects());
		System.out.println("");
		System.out.println(printAllTeachers());
	}
}
