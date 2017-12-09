package org.mac.nasbackup.components;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.mac.nasbackup.analyzer.AnalysisConfiguration;
import org.mac.nasbackup.analyzer.Operation;
import org.mac.nasbackup.persistance.DbAction;
import org.mac.nasbackup.persistance.dao.ImageDao;
import org.mac.nasbackup.persistance.dao.StorageDeviceDao;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.mac.nasbackup.persistance.service.FileMetaAnalyzer;
import org.mac.nasbackup.persistance.service.StorageDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("storageDeviceService")
public class StorageDeviceServiceImpl implements StorageDeviceService {
	private static final Logger logger = LoggerFactory.getLogger(StorageDeviceServiceImpl.class);
	
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

		switch (operation) {
		case INSERT_IF_NOT_EXISTS_ON_REFERENCE_DEVICE:
			Path p = Paths.get(new File(storageDevice.getPath()).toURI());
			//Check existance of files by the  storageDevice's path
			//Insert non existing
			fileAnalyzer.analyzeFolder(p, true, new DbAction<ImageEntry>() {

				@Override
				public int checkExistance(ImageEntry imageEntry) {
					return imageDao.identifyOnDevice(reference, imageEntry);
				}

				@Override
				public void handleDefault(AnalysisConfiguration cfg, int result, ImageEntry imageEntry) {
					logger.info("Skipping file, not sure how to handle it... ({}): {}", result, imageEntry);
				}

				@Override
				public void handleMissing(AnalysisConfiguration cfg, ImageEntry imageEntry) {
					logger.info("File is not present on reference-device. {}", imageEntry );
				}

				@Override
				public void handleMatch(AnalysisConfiguration cfg, ImageEntry imageEntry) {
					logger.info("File already present on reference-device. {}", imageEntry );
				}
			});
			
			break;
			case INSERT_WITHOUT_EXISTANCE_CHECK:
				throw new UnsupportedOperationException("INSERT_WITHOUT_EXISTANCE_CHECK is not allowed");
			case BUILD_REPORT:
				//Init report
				//Check existance of files by the  storageDevice's path
				//Build report
			break;
		default:
			break;
		}
		// Ta fram sökvägen till mappen som ska analyseras


	}

	@Override
	public int joinDevices(ImageEntry imageEntry, StorageDevice reference, Operation operation) {
		return imageDao.identifyOnDevice(reference, imageEntry);
	}

}
