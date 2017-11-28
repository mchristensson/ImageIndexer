package org.mac.nasbackup.analyzer;

import java.util.List;

import org.mac.nasbackup.persistance.model.ImageEntry;

public interface AnalysisConfiguration {
	
	/**
	 * The analysis result
	 * @return
	 */
	List<String> getAnalysisResult();

	/**
	 * Adds an exception to the result
	 * @param result 
	 * @param imageEntry
	 */
	void addException(int result, ImageEntry imageEntry);

	/**
	 * Increases the success counter
	 */
	void addSuccess();

	void addCount();

	void count();

	int getSuccess();

}