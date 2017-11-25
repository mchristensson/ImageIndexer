package org.mac.nasbackup.components;

import java.util.List;

import org.mac.nasbackup.persistance.dao.DeviceTypeDao;
import org.mac.nasbackup.persistance.model.DeviceType;
import org.mac.nasbackup.persistance.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceTypeService")
public class DeviceTypeServiceImpl implements DeviceTypeService {
	
	@Autowired
    DeviceTypeDao deviceTypeDao;

	@Override
	public void addDeviceType(DeviceType deviceType) {
		deviceTypeDao.addDeviceType(deviceType);
	}

	@Override
	public DeviceType find(long id) {
		return deviceTypeDao.find(id);
	}

	@Override
	public List<DeviceType> findAll() {
		return deviceTypeDao.findAll();
	}

}
