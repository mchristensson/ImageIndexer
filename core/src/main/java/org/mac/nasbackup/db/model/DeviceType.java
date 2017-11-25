package org.mac.nasbackup.db.model;

public class DeviceType {
	private int id;
	private String label;

	public DeviceType(String label) {
		this.label = (label);
	}

	public String getLabel() {
		return label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
