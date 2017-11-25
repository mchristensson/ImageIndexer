package persistance;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mac.nasbackup.persistance.app.ApplicationConfig;
import org.mac.nasbackup.persistance.model.DeviceType;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.service.DeviceTypeService;
import org.mac.nasbackup.persistance.service.ImageEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class TestApplication {
	
	private static AbstractApplicationContext context;
	private static final Logger logger = LoggerFactory.getLogger(TestApplication.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Test
	public void runAppTest() {
		ImageEntryService imgService = (ImageEntryService) context.getBean("imageService");
		DeviceTypeService deviceTypeService = (DeviceTypeService) context.getBean("deviceTypeService");
		
		ImageEntry file0 = new ImageEntry();
		file0.setFileName("abc.001");
		file0.setFilePath("c:/sdf/sdf/kljlkj/");
		file0.setModel("dsx-23");
		file0.setMake("Sony");
		file0.setSize(23456);
		file0.setSoftware("Paintbrush");
		imgService.addImageEntry(file0);
        
		logger.info("Find all ImageEntries...");
        List<ImageEntry> files = imgService.findAll();
        for (ImageEntry file: files) {
            logger.info("{}", file);
        }
        
        
        DeviceType deviceType0 = new DeviceType("Local URI");
        deviceTypeService.addDeviceType(deviceType0);
        
        logger.info("Find all DeviceTypes...");
        List<DeviceType> deviceTypes = deviceTypeService.findAll();
        for (DeviceType deviceType: deviceTypes) {
            logger.info("{}", deviceType);
        }
	}
}
