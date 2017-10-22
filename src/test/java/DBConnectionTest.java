import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class DBConnectionTest {

	DBConnection dbConnection;

	/**
	 * Setup
	 */
	@Before
	public void setUp() {
		dbConnection = new DBConnection();
	}

	/**
	 * Here I test if the dbConnection was successful

	@Test
	public void dbConnectionConnectionSuccessful(){
		assertTrue(dbConnection.connect());
	}
	 */

	/**
	 * Here I test that the while loop only run once and only create one database.
	 */

}
