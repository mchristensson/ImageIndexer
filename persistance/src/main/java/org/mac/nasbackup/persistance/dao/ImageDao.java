package org.mac.nasbackup.persistance.dao;

import java.util.List;

import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.model.StorageDevice;

public interface ImageDao {

	public void addImageEntry(ImageEntry person);

	public ImageEntry find(long id);

	public List<ImageEntry> findAll();

	/**
	 * Locates an entry on a device (read only)
	 * 
	 * @param referenceDevice The device to search on
	 * @param imageEntry The entity to look for
	 */
	public void identifyOnDevice(StorageDevice referenceDevice, ImageEntry imageEntry);
}
