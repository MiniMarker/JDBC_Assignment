import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DBHandler {

	private DBConnection dbCon;
	private String sqlQuery;


	public DBHandler() {
		dbCon = new DBConnection();
		dbCon.connect();
		}

	public void dropTablesIfExists() {
		try (Connection con = dbCon.ds.getConnection();
		     Statement stmt = con.createStatement()) {

			stmt.addBatch("DROP TABLE IF EXISTS Subject_Teacher;");
			stmt.addBatch("DROP TABLE IF EXISTS Subject;");
			stmt.addBatch("DROP TABLE IF EXISTS Teacher;");

			stmt.executeBatch();

		} catch (SQLException sqle){
			System.out.println("SQL ERROR! " + sqle.getMessage());
		}
	}

	public void createTables() {

		try (Connection con = dbCon.ds.getConnection();
		     Statement stmt = con.createStatement()) {

			stmt.executeUpdate(readSqlFile("target/textfiles/createSubjectTableSql.sql"));
			System.out.println("Subject table created...");

			stmt.executeUpdate(readSqlFile("target/textfiles/createTeacherTableSql.sql"));
			System.out.println("Teacher table created...");

			stmt.executeUpdate(readSqlFile("files/createSubjectTeacherTableSql.sql"));
			System.out.println("Subject_Teacher table created...");
		} catch (SQLException sqle){
			System.out.println("SQL ERROR! " + sqle.getMessage());
		}
	}

	private String readSqlFile(String filepath){

		try {
			BufferedReader sqlFileReader = new BufferedReader(new FileReader(filepath));

			StringBuilder stringBuilder = new StringBuilder();
			String line = sqlFileReader.readLine();

			while (line != null) {
				stringBuilder.append(line);
				line = sqlFileReader.readLine();
			}

			sqlQuery = stringBuilder.toString();

		} catch (IOException ioex){
			ioex.getMessage();
		}
		return sqlQuery;
	}

	/**
	 * Test methods
	 */

	public DBConnection getDbCon() {
		return dbCon;
	}
}
