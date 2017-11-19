package org.mac.nasbackup.db;

import java.util.List;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mac.nasbackup.db.model.ImageDao;
import org.mac.nasbackup.db.model.ImageEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ImgDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(ImgDaoTest.class);
	private static NamedParameterJdbcTemplate template;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("AppContext.xml");
		DataSource nasImageDatabase = (DataSource) context.getBean("nasImageDatabase");
		template = new NamedParameterJdbcTemplate(nasImageDatabase);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void findAll_emptyDb_returnzero() {
		logger.info("hello");
		
		ImageDao imgDao = new ImageDao();
		imgDao.setNamedParameterJdbcTemplate(template);
		List<ImageEntry> result = imgDao.findAll();
		Assert.assertEquals("Unexpected number of entries in database", 0, result.size());
		
		
	}
	
	@Test
	public void findAll_insertInEmptyDb_returnOne() {
		ImageDao imgDao = new ImageDao();
		imgDao.setNamedParameterJdbcTemplate(template);
		
		ImageEntry entry = new ImageEntry();
		entry.setId(4);
		imgDao.insert(entry);

		List<ImageEntry> resultb = imgDao.findAll();
		for (ImageEntry imageEntry : resultb) {
			logger.info(imageEntry.toString());
		}
		Assert.assertEquals("Unexpected number of entries in database", 1, resultb.size());
	}
	
	
	
}

