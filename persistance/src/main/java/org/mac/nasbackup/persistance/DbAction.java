package org.mac.nasbackup.persistance;

import org.mac.nasbackup.analyzer.AnalysisConfiguration;
import org.mac.nasbackup.persistance.model.ImageEntry;

public interface DbAction<T> {

	int checkExistance(ImageEntry imageEntry);

	void handleDefault(AnalysisConfiguration cfg, int result, ImageEntry imageEntry);

	void handleMissing(AnalysisConfiguration cfg, ImageEntry imageEntry);

	void handleMatch(AnalysisConfiguration cfg, ImageEntry imageEntry);
	

}

