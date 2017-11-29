package org.mac.nasbackup.persistance.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.mac.nasbackup.persistance.model.DeviceType;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.model.StorageDevice;
import org.springframework.jdbc.core.RowCallbackHandler;

public class ImageFileCallbackHandler implements RowCallbackHandler {
	private Collection<ImageEntry> imageEntries = new ArrayList<ImageEntry>();

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		ImageEntry imageEntry = new ImageEntry();
		imageEntry.setId(rs.getLong("FILE_ID"));
		imageEntry.setFileName(rs.getString("file_name"));
		imageEntry.setFilePath(rs.getString("file_path"));
		imageEntry.setSize(rs.getInt("file_size"));
		imageEntry.setMake(rs.getString("origin_make"));
		imageEntry.setModel(rs.getString("origin_model"));
		imageEntry.setSoftware(rs.getString("origin_software"));
		StorageDevice storageDevice = new StorageDevice();
		storageDevice.setId(rs.getLong("storagedevice_id"));
		storageDevice.setPath(rs.getString("file_path"));
		storageDevice.setLabel(rs.getString("label"));
		storageDevice.setDeviceType(DeviceType.valueOf(rs.getString("devicetype")));
		imageEntry.setStorageDevice(storageDevice);

		this.imageEntries.add(imageEntry);

	}

	public Collection<ImageEntry> getImageEntries() {
		return imageEntries;
	}

}