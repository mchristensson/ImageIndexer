package org.mac.nasbackup;

import java.io.File;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.mac.nasbackup.core.DefaultIndexBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ManagerApp implements Runnable {
	
	private NamedParameterJdbcTemplate template;
	
	public static void main(String[] args) {
		ManagerApp app = new ManagerApp();
		app.run();
	}

	public ManagerApp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");
		DataSource nasImageDatabase = (DataSource) context.getBean("nasImageDatabase");
		this.template = new NamedParameterJdbcTemplate(nasImageDatabase);
	}

	@Override
	public void run() {
		File f = Paths.get("/Users/magnus/Documents").toFile();
		Object result = new DefaultIndexBuilder().addIndexPipeline("imac", Paths.get(f.toURI()), true)
				.setDestination(Paths.get(f.toURI())).startIndex(template);
		
	}

}
