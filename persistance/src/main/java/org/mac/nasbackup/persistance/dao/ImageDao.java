package org.mac.nasbackup.persistance.dao;

import java.util.List;

import org.mac.nasbackup.persistance.model.ImageEntry;

public interface ImageDao {

	public void addImageEntry(ImageEntry person);

	public ImageEntry find(long id);

	public List<ImageEntry> findAll();
}
