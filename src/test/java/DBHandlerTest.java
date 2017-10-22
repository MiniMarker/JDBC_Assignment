import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBHandlerTest {


	DBHandler DBHandler;

	/**
	 * Setup
	 */
	@Before
	public void setUp() {
		DBHandler = new DBHandler();
	}

	/**
	 * Here I test if the dbConnection was successful

	@Test
	public void dbConnectionConnectionSuccessful(){
		assertTrue(DBHandler.getDbCon().connect());
	} */
}
