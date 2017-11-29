package org.mac.nasbackup.components;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.mac.nasbackup.analyzer.Operation;
import org.mac.nasbackup.persistance.DbAction;
import org.mac.nasbackup.persistance.dao.ImageDao;
import org.mac.nasbackup.persistance.dao.StorageDeviceDao;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.mac.nasbackup.persistance.service.FileMetaAnalyzer;
import org.mac.nasbackup.persistance.service.StorageDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("storageDeviceService")
public class StorageDeviceServiceImpl implements StorageDeviceService {

	@Autowired
	StorageDeviceDao storageDeviceDao;

	@Autowired
	ImageDao imageDao;

	@Autowired
	FileMetaAnalyzer fileAnalyzer;

	@Override
	public StorageDevice find(long id) {
		return storageDeviceDao.find(id);
	}

	@Override
	public List<StorageDevice> findAll() {
		return storageDeviceDao.findAll();
	}

	@Override
	public void addStorageDevice(StorageDevice storageDevice) {
		storageDeviceDao.addStorageDevice(storageDevice);
	}

	@Override
	public void joinDevices(StorageDevice storageDevice, StorageDevice reference, Operation operation) {

		// Ta fram sökvägen till mappen som ska analyseras
		Path p = Paths.get(new File(storageDevice.getPath()).toURI());
		
		//Vad är det för operation vi vill göra?
		// - kontrollera vilka filer som redan finns (100% match)
		// - kontrollera vilka filer som redan finns, gör insert på de som inte finns
		// - identifiera filer som inte matchar till 100%
		//Anropa analysatorn
		fileAnalyzer.analyzeFolder(p, true, new DbAction<ImageEntry>() {

			@Override
			public void performInsert(ImageEntry imageEntry) {
				// imageDao.addImageEntry(imageEntry);
				
			}

			@Override
			public int checkExistance(ImageEntry imageEntry) {
				return imageDao.identifyOnDevice(reference, imageEntry);
			}
		});

	}

	@Override
	public int joinDevices(ImageEntry imageEntry, StorageDevice reference, Operation operation) {
		return imageDao.identifyOnDevice(reference, imageEntry);
	}

}
