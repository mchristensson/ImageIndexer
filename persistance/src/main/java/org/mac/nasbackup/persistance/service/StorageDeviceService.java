package org.mac.nasbackup.persistance.service;

import java.util.List;

import org.mac.nasbackup.persistance.model.StorageDevice;

public interface StorageDeviceService {

	public void addStorageDevice(StorageDevice storageDevice);

	public StorageDevice find(long id);

	public List<StorageDevice> findAll();
}
