package org.mac.nasbackup.components;

import java.util.List;

import org.mac.nasbackup.persistance.dao.StorageDeviceDao;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("storageDeviceDao")
public class StorageDeviceDaoImpl implements StorageDeviceDao {
	private static final Logger logger = LoggerFactory.getLogger(StorageDeviceDaoImpl.class);

	@Autowired
	JdbcTemplate db;

	@Override
	public void addStorageDevice(StorageDevice storageDevice) {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding entity... {}", storageDevice);
		}
		db.update("INSERT INTO storagedevice (label, devicetype) VALUES (?, ?)", storageDevice.getLabel(),
				storageDevice.getDeviceType().name());
		int id = db.queryForObject("SELECT max(i.storagedevice_id) FROM storagedevice i", Integer.class);
		storageDevice.setId(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Entity inserted. id={}", id);
		}
	}

	@Override
	public StorageDevice find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StorageDevice> findAll() {
		int maxResult = 100;
		db.setMaxRows(100);
		List<StorageDevice> result = (List<StorageDevice>) db.query("SELECT * FROM storagedevice",
				new BeanPropertyRowMapper<StorageDevice>(StorageDevice.class));
		if (logger.isDebugEnabled()) {
			logger.debug("Found {} entries.", result.size());
		}
		if (logger.isWarnEnabled() && result.size() >= maxResult) {
			logger.warn("Find limit ({}) reached!", maxResult);
		}
		return result;
	}

}
