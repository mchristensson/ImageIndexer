package org.mac.nasbackup.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mac.nasbackup.db.model.DeviceType;
import org.mac.nasbackup.db.model.StorageDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class StorageDeviceDao implements GenericDao<StorageDevice> {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private final KeyHolder keyHolder = new GeneratedKeyHolder();
	
	@Override
	public int insert(StorageDevice entry) {
		int devicetypeId = findOrCreate(entry.getDeviceType()).getId();
		String sql = "insert into storagedevice(label,devicetype)" +
	       		" values(:label, "+devicetypeId +")";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entry);
		return namedParameterJdbcTemplate.update(sql, paramSource , keyHolder);
	}

	public List<DeviceType> findDeviceTypeUnique(DeviceType deviceType) {
		String sql = "SELECT id FROM devicetype WHERE label=:devicetype";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("devicetype", deviceType.getLabel());
		return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper(DeviceType.class));
	}
	
	private DeviceType findOrCreate(DeviceType deviceType) {
		List<DeviceType> result = findDeviceTypeUnique(deviceType);
		if (result.isEmpty()) {
			return insertDeviceType(deviceType);
		} else {
			return result.get(0);
		}
	}
	public DeviceType insertDeviceType(DeviceType entry) {
		String sqlB = "insert into devicetype(label)" +
	       		" values(:label)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(entry);
	    int id = namedParameterJdbcTemplate.update(sqlB, paramSource , keyHolder);
	    entry.setId(id);
	    return entry;
	}

	@Override
	public List<StorageDevice> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM storagedevice";
		List<StorageDevice> result = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper(StorageDevice.class));
		return result;
	}

	@Override
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

}
