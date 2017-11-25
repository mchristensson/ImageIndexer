package org.mac.nasbackup.persistance.dao;

import java.util.List;

import org.mac.nasbackup.persistance.model.DeviceType;

public interface DeviceTypeDao {
	
	public void addDeviceType(DeviceType person);

	public DeviceType find(long id);

	public List<DeviceType> findAll();
}
