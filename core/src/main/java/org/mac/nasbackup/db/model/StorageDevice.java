package org.mac.nasbackup.db.model;

public class StorageDevice {
	private int id;
	private String label;
	private DeviceType deviceType;

	public void setId(int int1) {
		this.id = int1;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
}
