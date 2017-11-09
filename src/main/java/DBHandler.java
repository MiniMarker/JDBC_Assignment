import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DBHandler {

	private DBConnection dbCon;
	private String result;


	public DBHandler() {
		dbCon = new DBConnection();
		dbCon.connect();
	}

	/**
	 * Runs a query that drops the table if it exists.
	 * @param connection a MySQLDataSource
	 * @return a boolean to check if the deletion was successful.
	 */
	public boolean dropTablesIfExists(Connection connection) {
		try (Connection con = connection;
		     Statement stmt = con.createStatement()) {

			stmt.addBatch("DROP TABLE IF EXISTS Subject;");
			stmt.addBatch("DROP TABLE IF EXISTS Teacher;");
			stmt.executeBatch();

			return true;

		} catch (SQLException sqle) {
			System.out.println("SQL ERROR! " + sqle.getMessage());
		}
		return false;
	}

	/**
	 * this method uses readSqlFile(String filepath) to run a query that creates the tables.
	 * @param connection a MySQLDataSource
	 */
	public void createTables(Connection connection) {

		try (Connection con = connection;
		     Statement stmt = con.createStatement()) {

			stmt.executeUpdate(readSqlFile("target/textfiles/createSubjectTableSql.sql"));
			System.out.println("Subject table created...");

			stmt.executeUpdate(readSqlFile("target/textfiles/createTeacherTableSql.sql"));
			System.out.println("Teacher table created...");

		} catch (SQLException sqle) {
			System.out.println("SQL ERROR! " + sqle.getMessage());
		}
	}

	/**
	 * This method deletes the intire database... USE WITH CAUTION!!!!!!
	 * @param connection a MySQLDataSource
	 * @return a boolean to check if the deletion was successful
	 */
	/*protected boolean destroyDatabase(Connection connection)    {

		try (Connection con = connection;
		     Statement stmt = con.createStatement()) {

			stmt.executeQuery("DROP SCHEMA " + connection.getSchema());

			return true;
		} catch (SQLException sqle){
			sqle.getMessage();
		}
		return false;
	} */

	/**
	 * This method reads text from a file and returns it as a string.
	 * @param filepath the absolute file path to the .txt file.
	 * @return a string of the file by using a StringBuilder, in this intance a SQL-string.
	 */
	private String readSqlFile(String filepath) {

		try (BufferedReader sqlFileReader = new BufferedReader(new FileReader(filepath))) {

			StringBuilder stringBuilder = new StringBuilder();
			String line = sqlFileReader.readLine();

			while (line != null) {
				stringBuilder.append(line + " ");
				line = sqlFileReader.readLine();
			}

			result = stringBuilder.toString();

		} catch (IOException ioex) {
			ioex.getMessage();
		}
		return result;
	}
}
