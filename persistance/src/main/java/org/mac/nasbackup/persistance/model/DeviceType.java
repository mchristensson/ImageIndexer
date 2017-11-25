package org.mac.nasbackup.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "DeviceType")
@Table(name = "devicetype")
public class DeviceType {
	private long id;
	private String label;

	public DeviceType(String label) {
		this.label = (label);
	}

	@Column(name = "label", nullable = true)
	public String getLabel() {
		return label;
	}

	@Column(name = "devicetype_id", nullable = false)
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

}
