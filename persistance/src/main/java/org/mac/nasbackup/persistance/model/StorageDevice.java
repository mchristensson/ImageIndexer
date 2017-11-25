package org.mac.nasbackup.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "StorageDevice")
@Table(name = "storagedevice")
public class StorageDevice {
	private String label;
	private long id;
	private DeviceType deviceType;

	public StorageDevice() {
	}

	@Column(name = "label", nullable = false)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setId(int int1) {
		this.id = int1;
	}

	@Column(name = "storagedevice_id", nullable = false)
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	@Column(name = "devicetype", nullable = false)
	@Enumerated(EnumType.STRING)
	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return String.format("StorageDevice [id=%s, deviceType=%s, label=%s]", id, deviceType, label);
	}
}
