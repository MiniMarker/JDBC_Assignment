import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection  {

	private String dbName;
	private String host;
	private String userName;
	private String password;

	public DBConnection() {
		readConfigFile();
	}

	/**
	 * establishes a new MySqlDataSource bu using the properties of the fields
	 * @return A connection to the database
	 */
	public Connection connect() {
		try {
			MysqlDataSource ds = new MysqlDataSource();
			ds.setDatabaseName(dbName);
			ds.setServerName(host);
			ds.setUser(userName);
			ds.setPassword(password);

			return ds.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * reads the propertyfile and setting the fields to its data for further use.
	 */
	private void readConfigFile() {
		Properties props = new Properties();

		String filepath = "dbConfig.properties";

		try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream(filepath)) {

			if (input == null) {
				throw new FileNotFoundException();
			}

			props.load(input);

			dbName = (props.getProperty("dbName"));
			host = (props.getProperty("host"));
			userName = (props.getProperty("username"));
			password = (props.getProperty("password"));

		} catch (IOException ioex) {
			ioex.getMessage();
		}
	}


	/**
	 * does a setup check that connects to the mySQL server and creates the schema
	 */
	public void setupCheck() {

		try (Connection con = DriverManager.getConnection("jdbc:mysql://" + host + "/?user=" + userName + "&password=" + password);
		     Statement stmt = con.createStatement()) {

			Class.forName("com.mysql.jdbc.Driver");

			stmt.addBatch("DROP SCHEMA IF EXISTS " + dbName);
			stmt.addBatch("CREATE SCHEMA " + dbName);
			stmt.executeBatch();

			System.out.println("\n" + dbName + " created..");

		} catch (SQLException sqle) {
			System.out.println("SQL ERROR! " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("ClassNotFoundError! " + cnfe.getMessage());
		}
	}
}

