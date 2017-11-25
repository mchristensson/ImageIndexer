package org.mac.nasbackup.components;

import java.util.Collections;
import java.util.List;

import org.mac.nasbackup.persistance.dao.DeviceTypeDao;
import org.mac.nasbackup.persistance.model.DeviceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
@Repository
@Qualifier("deviceTypeDao")
public class DeviceTypeDaoImpl implements DeviceTypeDao {

	@Override
	public void addDeviceType(DeviceType person) {
		// TODO Auto-generated method stub

	}

	@Override
	public DeviceType find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceType> findAll() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

}
