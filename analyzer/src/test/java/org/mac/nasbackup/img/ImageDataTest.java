package org.mac.nasbackup.img;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.drew.imaging.ImageProcessingException;

public class ImageDataTest {

	@Test
	public void checkTestFileExists() {
		File f = new File("./src/test/resources/images/tiger.jpg");
		Assert.assertTrue("File does not exist.", f.exists());
	}
	
	
	
	@Test
	public void retrieveJpegExif() {
		File file = new File("./src/test/resources/images/IMG_1201.jpg");

		try {
			Map<String,String> map = ImageData.getMetaDataMap(file);
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() +  " : " + entry.getValue());
			}
			
			
		} catch (ImageProcessingException | IOException  e) {
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void retrievePngExif() {

		File file = new File("./src/test/resources/images/IMG_1262.PNG");

		try {
			
			Map<String,String> map = ImageData.getMetaDataMap(file);
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() +  " : " + entry.getValue());
			}
			
			
		} catch (ImageProcessingException | IOException  e) {
			Assert.fail(e.getMessage());
		}

	}
}
