package org.mac.nasbackup.persistance.service;

import java.util.Collection;
import java.util.List;

import org.mac.nasbackup.persistance.model.ImageEntry;

public interface ImageEntryService {

	public void addImageEntry(ImageEntry person);

	public ImageEntry find(long id);

	public Collection<ImageEntry> findAll();
}
