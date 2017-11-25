package org.mac.nasbackup.persistance;

import java.util.List;

import org.mac.nasbackup.persistance.app.ApplicationConfig;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.service.ImageEntryService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class DbApplication implements Runnable {
	
	
	public DbApplication() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        
		ImageEntryService imgService = (ImageEntryService) context.getBean("imageService");
		
		ImageEntry file0 = new ImageEntry();
		file0.setFileName("abc.001");
		file0.setFilePath("c:/sdf/sdf/kljlkj/");
		file0.setModel("dsx-23");
		file0.setMake("Sony");
		file0.setSize(23456);
		file0.setSoftware("Paintbrush");
		 
		imgService.addImageEntry(file0);
        
        System.out.println("Find All");
        List<ImageEntry> files = imgService.findAll();
        for (ImageEntry file: files) {
            System.out.println(file);
        }
		
	}

	@Override
	public void run() {
		System.out.println("Dstarted");
		
		System.out.println("Done");
	}

}
