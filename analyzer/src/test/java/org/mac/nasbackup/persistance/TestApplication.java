package org.mac.nasbackup.persistance;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mac.nasbackup.analyzer.Operation;
import org.mac.nasbackup.config.ApplicationConfig;
import org.mac.nasbackup.persistance.model.DeviceType;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.mac.nasbackup.persistance.service.ImageEntryService;
import org.mac.nasbackup.persistance.service.Match;
import org.mac.nasbackup.persistance.service.StorageDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class TestApplication {
	
	private static AbstractApplicationContext context;
	private static final Logger logger = LoggerFactory.getLogger(TestApplication.class);
	private ImageEntryService imgService;
	private StorageDeviceService deviceTypeService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Before
	public void initServices() {
		 imgService = (ImageEntryService) context.getBean("imageService");
		 deviceTypeService = (StorageDeviceService) context.getBean("storageDeviceService");
	}
	
	@Ignore
	@Test
	public void runAppTest() {
		StorageDevice storageDevice0 = new StorageDevice();
        storageDevice0.setDeviceType(DeviceType.EXT_HD);
        storageDevice0.setLabel("Gammalsunk 2.5 tum");
        deviceTypeService.addStorageDevice(storageDevice0);
        StorageDevice storageDevice1 = new StorageDevice();
        storageDevice1.setDeviceType(DeviceType.MOBILE_PHONE);
        storageDevice1.setLabel("Klaras IPhone 4");
        deviceTypeService.addStorageDevice(storageDevice1);
        StorageDevice storageDevice2 = new StorageDevice();
        storageDevice2.setDeviceType(DeviceType.NAS);
        storageDevice2.setLabel("Mitt NAS");
        deviceTypeService.addStorageDevice(storageDevice2);
        
        logger.info("Find all StorageDevices...");
        List<StorageDevice> storageDevices = deviceTypeService.findAll();
        for (StorageDevice storageDevice: storageDevices) {
            logger.info("{}", storageDevice);
        }
		
        ImageEntry file2 = new ImageEntry();
        file2.setFileName("abc.001");
        file2.setFilePath("d:/hgfhgflkj/");
        file2.setModel("dsx-23");
        file2.setMake("Samsung");
        file2.setSize(77456);
        file2.setSoftware("Photocream");
        file2.setStorageDevice(storageDevice0);
		imgService.addImageEntry(file2);
        
		ImageEntry file1 = new ImageEntry();
		file1.setFileName("abc.001");
		file1.setFilePath("c:/sdf/sdf/kljlkj/");
		file1.setModel("dsx-23");
		file1.setMake("Sony");
		file1.setSize(77456);
		file1.setSoftware("Paintbrush");
		file1.setStorageDevice(storageDevice0);
		imgService.addImageEntry(file1);
		
		ImageEntry file0 = new ImageEntry();
		file0.setFileName("abc.001");
		file0.setFilePath("c:/sdf/sdf/kljlkj/");
		file0.setModel("dsx-23");
		file0.setMake("Sony");
		file0.setSize(23456);
		file0.setSoftware("Paintbrush");
		file0.setStorageDevice(storageDevice1);
		imgService.addImageEntry(file0);
        
		logger.info("Find all ImageEntries...");
        Collection<ImageEntry> files = imgService.findAll();
        logger.info("Found {} entries in total. ", files.size());
        
        logger.info("Find ImageEntry matches in database...");
        Assert.assertEquals("Did not expect to find this file on this device",Match.NO_MATCH, deviceTypeService.joinDevices(file0, storageDevice2, Operation.CHECK_EXISTANCE_ON_TARGET_DEVICE));
        Assert.assertEquals("Actually expected to find this file on this device",Match.FULL_MATCH_AND_SAME_DEVICE, deviceTypeService.joinDevices(file0, storageDevice1, Operation.CHECK_EXISTANCE_ON_TARGET_DEVICE));
        Assert.assertEquals("Did not expect to find this file on this device",Match.NO_MATCH, deviceTypeService.joinDevices(file0, storageDevice0, Operation.CHECK_EXISTANCE_ON_TARGET_DEVICE));
        
        ImageEntry file4 = new ImageEntry();
        file4.setFileName("abc.001");
        file4.setFilePath("c:/sdf/sdf/kljlkj/");
        file4.setModel("dfhgdgfd");
        file4.setMake("hgfdhgdhgf");
        file4.setSize(23456);
        file4.setSoftware("hgfdhgfdhgfd");
        file4.setStorageDevice(null);
		Assert.assertEquals("Actually expected to find this file on this device",Match.MATCH_ON_FILE_AND_SIZE, deviceTypeService.joinDevices(file4, storageDevice1, Operation.CHECK_EXISTANCE_ON_TARGET_DEVICE));
		file4.setSize(6758675);
		Assert.assertEquals("Actually expected to find this file on this device",Match.NO_MATCH, deviceTypeService.joinDevices(file4, storageDevice1, Operation.CHECK_EXISTANCE_ON_TARGET_DEVICE));
		
	}
	
	@Test
	public void runAppTest_B() {
		StorageDevice nas = new StorageDevice();
		nas.setDeviceType(DeviceType.NAS);
		nas.setLabel("Mitt NAS");
        deviceTypeService.addStorageDevice(nas);
        
        StorageDevice folder = new StorageDevice();
        folder.setDeviceType(DeviceType.LOCAL_URI);
        folder.setLabel("Min folder");
        folder.setPath("./hello");
		try {
			deviceTypeService.joinDevices(folder, nas, Operation.CHECK_EXISTANCE_ON_TARGET_DEVICE);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
}
