package org.mac.nasbackup.persistance.service;

import java.util.List;

import org.mac.nasbackup.analyzer.Operation;
import org.mac.nasbackup.persistance.model.StorageDevice;

public interface StorageDeviceService {

	/**
	 * Adds a new storage device to database
	 * @param storageDevice
	 */
	public void addStorageDevice(StorageDevice storageDevice);

	/**
	 * Validates whether storage device's files are present in reference device
	 * 
	 * @param storageDevice the storage device to analyze
	 * @param reference reference target device (Read only)
	 * @param operation
	 */
	public void joinDevices(StorageDevice storageDevice, StorageDevice reference, Operation operation);
	
	public StorageDevice find(long id);

	public List<StorageDevice> findAll();
	
	
}
