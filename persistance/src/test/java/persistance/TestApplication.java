package persistance;

import org.junit.Test;
import org.mac.nasbackup.persistance.DbApplication;

public class TestApplication {

	@Test
	public void runAppTest() {
		DbApplication app = new DbApplication();
		app.run();
	}
}
