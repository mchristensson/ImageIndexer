package org.mac.nasbackup.components;

import java.util.List;

import org.mac.nasbackup.persistance.dao.ImageDao;
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
		if (logger.isDebugEnabled()) {
			logger.debug("Adding entity... {}", imageEntry);
		}
		db.update(
				"INSERT INTO imageFiles (file_name, file_path, origin_make, "
						+ "origin_model, file_size, origin_software) VALUES (?, ?, ?, ?, ?, ?)",
				imageEntry.getFileName(), imageEntry.getFilePath(), imageEntry.getMake(), imageEntry.getModel(),
				imageEntry.getSize(), imageEntry.getSoftware());
		if (logger.isDebugEnabled()) {
			logger.debug("Entity inserted.", imageEntry);
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
	public List<ImageEntry> findAll() {
		int maxResult = 100;
		db.setMaxRows(100);
		List<ImageEntry> result = (List<ImageEntry>) db.query("SELECT * FROM imageFiles",
				new BeanPropertyRowMapper<ImageEntry>(ImageEntry.class));
		if (logger.isDebugEnabled()) {
			logger.debug("Found {} entries.", result.size());
		}
		if (logger.isWarnEnabled() && result.size() >= maxResult) {
			logger.warn("Find limit ({}) reached!", maxResult);
		}
		return result;
	}

	@Override
	public void identifyOnDevice(StorageDevice referenceDevice, ImageEntry imageEntry) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
