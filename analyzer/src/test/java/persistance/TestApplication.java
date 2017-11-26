package persistance;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mac.nasbackup.config.ApplicationConfig;
import org.mac.nasbackup.persistance.model.DeviceType;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.mac.nasbackup.persistance.service.ImageEntryService;
import org.mac.nasbackup.persistance.service.StorageDeviceService;
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
		StorageDeviceService deviceTypeService = (StorageDeviceService) context.getBean("storageDeviceService");
		
		StorageDevice storageDevice0 = new StorageDevice();
        storageDevice0.setDeviceType(DeviceType.EXT_HD);
        storageDevice0.setLabel("Gammalsunk 2.5 tum");
        deviceTypeService.addStorageDevice(storageDevice0, false);
        StorageDevice storageDevice1 = new StorageDevice();
        storageDevice1.setDeviceType(DeviceType.MOBILE_PHONE);
        storageDevice1.setLabel("Klaras IPhone 4");
        deviceTypeService.addStorageDevice(storageDevice1, false);
        
        logger.info("Find all StorageDevices...");
        List<StorageDevice> storageDevices = deviceTypeService.findAll();
        for (StorageDevice storageDevice: storageDevices) {
            logger.info("{}", storageDevice);
        }
		
		ImageEntry file0 = new ImageEntry();
		file0.setFileName("abc.001");
		file0.setFilePath("c:/sdf/sdf/kljlkj/");
		file0.setModel("dsx-23");
		file0.setMake("Sony");
		file0.setSize(23456);
		file0.setSoftware("Paintbrush");
		file0.setStorageDevice(storageDevice0);

		imgService.addImageEntry(file0);
        
		logger.info("Find all ImageEntries...");
        List<ImageEntry> files = imgService.findAll();
        for (ImageEntry file: files) {
            logger.info("{}", file);
        }
        
        
        
	}
}
