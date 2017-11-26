package org.mac.nasbackup.persistance.service;

import java.util.List;

import org.mac.nasbackup.persistance.model.StorageDevice;

public interface StorageDeviceService {

	/**
	 * Adds a new storage device to database
	 * @param storageDevice
	 * @param insertFiles Whether files should be added to database or not 
	 */
	public void addStorageDevice(StorageDevice storageDevice, boolean insertFiles);

	/**
	 * Validates whether storage device's files are present in reference device
	 * @param storageDevice the storage device to analyze
	 * @param reference reference target device (Read only)
	 */
	public void analyzeStorageDeviceFiles(StorageDevice storageDevice, StorageDevice reference);
	
	public StorageDevice find(long id);

	public List<StorageDevice> findAll();
	
	
}
