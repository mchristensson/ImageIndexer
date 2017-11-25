package org.mac.nasbackup.components;

import java.util.List;

import org.mac.nasbackup.persistance.dao.StorageDeviceDao;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.mac.nasbackup.persistance.service.StorageDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("storageDeviceService")
public class StorageDeviceServiceImpl implements StorageDeviceService {

	@Autowired
	StorageDeviceDao storageDeviceDao;

	@Override
	public void addStorageDevice(StorageDevice storageDevice) {
		storageDeviceDao.addStorageDevice(storageDevice);
	}

	@Override
	public StorageDevice find(long id) {
		return storageDeviceDao.find(id);
	}

	@Override
	public List<StorageDevice> findAll() {
		return storageDeviceDao.findAll();
	}

}
