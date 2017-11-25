CREATE TABLE devicetype (
  devicetype_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  label VARCHAR(300),
  PRIMARY KEY (devicetype_id)
);

CREATE TABLE storagedevice (
  storagedevice_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  label VARCHAR(300),
  devicetype INTEGER,
  FOREIGN KEY(devicetype) REFERENCES devicetype(devicetype_id),
  PRIMARY KEY (storagedevice_id)
);

CREATE TABLE imagefiles (
  file_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  file_name VARCHAR(300),
  file_path  VARCHAR(500),
  file_size INTEGER,
  origin_make VARCHAR(300),
  origin_model VARCHAR(300),
  origin_software VARCHAR(300),
  storagedevice INTEGER,
  FOREIGN KEY(storagedevice) REFERENCES storagedevice(storagedevice_id),
  PRIMARY KEY (file_id)
);



