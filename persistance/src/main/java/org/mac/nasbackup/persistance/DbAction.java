package org.mac.nasbackup.persistance;

import org.mac.nasbackup.persistance.model.ImageEntry;

public interface DbAction<T> {

	void perform(ImageEntry imageEntry);

}

