package org.mac.nasbackup.persistance.service;

import java.util.List;

import org.mac.nasbackup.persistance.model.DeviceType;

public interface DeviceTypeService {
	
	public void addDeviceType(DeviceType person);

	public DeviceType find(long id);

	public List<DeviceType> findAll();
}
