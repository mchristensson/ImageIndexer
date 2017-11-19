package org.mac.nasbackup.db.model;

public class ImageEntry {
	private int id, size;
	private String fileName;
	private String filePath;
	private String make;
	private String model;
	private String software;

	public String getSoftware() {
		return software;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public void setId(int int1) {
		this.id = int1;
	}

	public int getId() {
		return id;
	}

	public int getSize() {
		return size;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getFilePath() {
		return this.filePath;
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

	@Override
	public String toString() {
		return String.format("ImageEntry [id=%s, size=%s, fileName=%s, filePath=%s]", id, size, fileName, filePath);
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

}