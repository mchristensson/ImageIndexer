package org.mac.nasbackup.components;

import java.util.List;

import org.mac.nasbackup.persistance.dao.ImageDao;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.service.ImageEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl implements ImageEntryService {

	@Autowired
	ImageDao imageDao;

	@Override
	public void addImageEntry(ImageEntry imageEntry) {
		imageDao.addImageEntry(imageEntry);
	}

	@Override
	public ImageEntry find(long id) {
		return imageDao.find(id);
	}

	@Override
	public List<ImageEntry> findAll() {
		return imageDao.findAll();
	}

}
