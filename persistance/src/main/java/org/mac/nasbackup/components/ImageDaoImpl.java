package org.mac.nasbackup.components;

import java.util.Collection;
import java.util.List;

import org.mac.nasbackup.persistance.dao.ImageDao;
import org.mac.nasbackup.persistance.mapper.ImageFileCallbackHandler;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("imageDao")
public class ImageDaoImpl implements ImageDao {

	private static final Logger logger = LoggerFactory.getLogger(ImageDaoImpl.class);

	@Autowired
	JdbcTemplate db;

	@Override
	public void addImageEntry(ImageEntry imageEntry) {
		db.update(
				"INSERT INTO imageFiles (file_name, file_path, origin_make, "
						+ "origin_model, file_size, origin_software, storagedevice) VALUES (?, ?, ?, ?, ?, ?, ?)",
				imageEntry.getFileName(), imageEntry.getFilePath(), imageEntry.getMake(), imageEntry.getModel(),
				imageEntry.getSize(), imageEntry.getSoftware(), imageEntry.getStorageDevice().getId());
		int id = db.queryForObject("SELECT max(i.file_id) FROM imageFiles i", Integer.class);
		imageEntry.setId(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Entity inserted. {}", imageEntry);
		}
	}

	@Override
	public ImageEntry find(long id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Searching for entry with id {}...", id);
		}
		return (ImageEntry) db.queryForObject("SELECT * FROM imageFiles where file_id = ? ", new Object[] { id },
				new BeanPropertyRowMapper<ImageEntry>(ImageEntry.class));
	}

	@Override
	public Collection<ImageEntry> findAll() {
		int maxResult = 100;
		db.setMaxRows(100);
		ImageFileCallbackHandler imageFileCallbackHandler = new ImageFileCallbackHandler();
		db.query(
				"SELECT i.*,d.* FROM imageFiles i left outer join StorageDevice d on d.storagedevice_id = i.storagedevice",
				imageFileCallbackHandler, new Object[] {});
		Collection<ImageEntry> result = imageFileCallbackHandler.getImageEntries();
		if (logger.isDebugEnabled()) {
			logger.debug("Found {} entries.", result.size());
		}
		if (logger.isWarnEnabled() && result.size() >= maxResult) {
			logger.warn("Find limit ({}) reached!", maxResult);
		}
		return result;
	}

	@Override
	public int identifyOnDevice(StorageDevice referenceDevice, ImageEntry imageEntry) {
		if (logger.isDebugEnabled()) {
			logger.debug("Searching for entry within device '{}' of type '{}'...", referenceDevice.getLabel(),
					referenceDevice.getDeviceType().name());
		}
		ImageFileCallbackHandler imageFileCallbackHandler = new ImageFileCallbackHandler();

		db.query(
				"SELECT i.*,d.* FROM imageFiles i "
				+ "left outer join StorageDevice as d on d.storagedevice_id = i.storagedevice "
				+ "where (i.storagedevice=?) AND (i.file_name =?) AND (i.file_size = ?)",
				imageFileCallbackHandler, new Object[] { referenceDevice.getId(), imageEntry.getFileName(), imageEntry.getSize() });
		Collection<ImageEntry> result = imageFileCallbackHandler.getImageEntries();
		return result.size();
	}
}
