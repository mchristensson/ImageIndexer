package org.mac.nasbackup.analyzer;

import java.util.Comparator;

import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.service.Match;

public class ImageEntryComparator implements Comparator<ImageEntry> {

	@Override
	public int compare(ImageEntry o1, ImageEntry o) {
		
		if (o1.getFileName().equals(o.getFileName()) && 
				o1.getMake().equals(o.getMake())&& 
				o1.getModel().equals(o.getModel())&& 
				o1.getSize() == o.getSize()&& 
						o1.getSoftware().equals(o.getSoftware())) {
			if (o1.getStorageDevice().getId()==o.getStorageDevice().getId()) {
				return Match.FULL_MATCH_AND_SAME_DEVICE;
			} else {
				return Match.MATCH_ON_FILE_AND_SIZE_AND_MAKE_AND_MODEL_AND_SOFTWARE;				
			}
		} else if 
		 (o1.getFileName().equals(o.getFileName()) && 
				o1.getSize() == o.getSize()) {
			return Match.MATCH_ON_FILE_AND_SIZE;
		} else {
			return Match.ERROR;
		}
		
	}

}
