package org.mac.nasbackup.components;

import java.util.Iterator;
import java.util.List;

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
	public void addStorageDevice(StorageDevice storageDevice, boolean insertFiles) {
		if (insertFiles) {
			throw new UnsupportedOperationException("Insertion of file data not yet supported.");			
		}
		storageDeviceDao.addStorageDevice(storageDevice);
	}

	@Override
	public void analyzeStorageDeviceFiles(StorageDevice storageDevice, StorageDevice reference) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Analysis of file data not yet supported.");
		
		//Analyze every file on 'storageDevice'
		//Collection storageDeviceFiles;
		//for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			
			fileAnalyzer.analyzeFolder(null, true, new DbAction<ImageEntry>() {
				
				@Override
				public void perform(ImageEntry imageEntry) {
					imageDao.addImageEntry(imageEntry);
				}
			});
			
	//		imageDao.findOnDevice(reference.getId(), )
		
	}

}
