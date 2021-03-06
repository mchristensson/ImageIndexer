package org.mac.nasbackup.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "ImageEntry")
@Table(name = "imageFiles")
public class ImageEntry implements Comparable<ImageEntry>{
	private long id;
	private int size;
	private String fileName;
	private String filePath;
	private String make;
	private String model;
	private String software;

	private StorageDevice storageDevice;

	@Column(name = "origin_software", nullable = true)
	public String getSoftware() {
		return software;
	}

	@Column(name = "origin_make", nullable = true)
	public String getMake() {
		return make;
	}

	@Column(name = "origin_model", nullable = true)
	public String getModel() {
		return model;
	}

	public void setId(long int1) {
		this.id = int1;
	}

	@Column(name = "file_id", nullable = false)
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	@Column(name = "file_size", nullable = false)
	public int getSize() {
		return size;
	}

	@Column(name = "file_name", nullable = false)
	public String getFileName() {
		return this.fileName;
	}

	@Column(name = "file_path", nullable = false)
	public String getFilePath() {
		return this.filePath;
	}

	@ManyToOne(targetEntity = StorageDevice.class)
	@JoinColumn(name = "storagedevice", referencedColumnName="storagedevice_id")
	public StorageDevice getStorageDevice() {
		return storageDevice;
	}

	public void setSize(int int1) {
		this.size = int1;
	}

	public void setFilePath(String string) {
		this.filePath = string;
	}

	public void setFileName(String string) {
		this.fileName = string;
	}

	public void setSizeFromString(String s) {
		if (s != null && s.length() > 0) {
			this.size = Integer.parseInt(s);
		}
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	@Override
	public String toString() {
		return String.format(
				"ImageEntry [id=%s, size=%s, fileName=%s, filePath=%s, make=%s, model=%s, software=%s, storageDevice=%s]",
				id, size, fileName, filePath, make, model, software, storageDevice);
	}

	public void setStorageDevice(StorageDevice storageDevice) {
		this.storageDevice = storageDevice;
	}

	@Override
	public int compareTo(ImageEntry o) {
		if (this.fileName.equals(o.getFileName()) && 
				this.getMake().equals(o.getMake())&& 
				this.getModel().equals(o.getModel())&& 
				this.getSize() == o.getSize()&& 
				this.getSoftware() == o.getSoftware()) {
			
		} 
		return 0;
	}
	
	

}
