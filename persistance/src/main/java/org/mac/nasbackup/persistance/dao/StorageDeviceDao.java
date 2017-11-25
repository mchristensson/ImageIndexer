package org.mac.nasbackup.persistance.dao;

import java.util.List;

import org.mac.nasbackup.persistance.model.StorageDevice;

public interface StorageDeviceDao {

	public void addStorageDevice(StorageDevice storageDevice);

	public StorageDevice find(long id);

	public List<StorageDevice> findAll();
}
