import org.junit.*;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Christian on 05.11.2017.
 */

public class DatabaseTests {

	private TestDBConnection testCon = new TestDBConnection();

	@BeforeClass
	public static void setup(){
		TestDBConnection testCon = new TestDBConnection();
		assertTrue(testCon.setupCheck());

		DBHandler dbHandler = new DBHandler();
		dbHandler.dropTablesIfExists(testCon.connect());
		dbHandler.createTables(testCon.connect());
	}

	@AfterClass
	public static void tearDown() throws SQLException {
		TestDBConnection testCon = new TestDBConnection();

		Statement stmt = testCon.connect().createStatement();

		stmt.executeUpdate("DROP SCHEMA TestTimetableWesterdals");
	}

	@Test
	public void subjectSetUpCheckTest() throws SQLException {
		Statement stmt = testCon.connect().createStatement();

		ResultSet subjectResultSet = stmt.executeQuery("SELECT * FROM Subject");
		ResultSetMetaData subjectResultsetMetaData = subjectResultSet.getMetaData();

		String[] subjectAttributteNames = {"code", "name", "duration", "numStudents"};

		for (int i = 0; i < subjectResultsetMetaData.getColumnCount(); i++) {
			assertEquals(subjectAttributteNames[i], subjectResultsetMetaData.getColumnName(i + 1));
		}
	}

	@Test
	public void teacherSetUpCheckTest() throws SQLException {
		Statement stmt = testCon.connect().createStatement();

		ResultSet teacherResultSet = stmt.executeQuery("SELECT * FROM Teacher");
		ResultSetMetaData teacherResultSetMetaData = teacherResultSet.getMetaData();

		String[] teacherAttributteNames = {"id", "name", "notAvailable", "contact"};

		for (int i = 0; i < teacherResultSetMetaData.getColumnCount(); i++) {
			assertEquals(teacherAttributteNames[i], teacherResultSetMetaData.getColumnName(i+1));
		}
	}

	@Test
	public void getSubjectPrimaryKeyTest() throws SQLException{
		DatabaseMetaData subjectMetaData = testCon.connect().getMetaData();
		ResultSet rs = subjectMetaData.getPrimaryKeys(null, "TestTimetableWesterdals", "Subject");

		while (rs.next()){
			assertEquals("code", rs.getString(4));
		}
	}

	@Test
	public void getTeacherPrimaryKeyTest() throws SQLException{
		DatabaseMetaData teacherMetaData = testCon.connect().getMetaData();
		ResultSet rs = teacherMetaData.getPrimaryKeys(null, "TestTimetableWesterdals", "Teacher");

		while (rs.next()){
			assertEquals("id", rs.getString(4));
		}
	}

	@Test
	public void subjectTableIsFilledTest() throws SQLException {
		InputHandler inputHandler = new InputHandler();
		inputHandler.addSubjectDataFromFile(testCon.connect());

		Statement stmt = testCon.connect().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Subject");

		int rowCount = rs.last() ? rs.getRow() : 0;

		assertEquals(10, rowCount);
	}

	@Test
	public void teacherTableIsFilledTest() throws SQLException {
		InputHandler inputHandler = new InputHandler();
		inputHandler.addTeacherDataFromFile(testCon.connect());

		Statement stmt = testCon.connect().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Teacher");

		int rowCount = rs.last() ? rs.getRow() : 0;

		assertEquals(5, rowCount);
	}
}