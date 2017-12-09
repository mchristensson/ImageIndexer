package org.mac.nasbackup.persistance.service;

import java.nio.file.Path;
import java.util.List;

import org.mac.nasbackup.persistance.DbAction;
import org.mac.nasbackup.persistance.model.ImageEntry;


public interface FileMetaAnalyzer {

	/**
	 * Defines a file-tree traverse operation.
	 *  
	 * @param path File path to traverse from
	 * @param recursive Whether analysis should include sub-directories
	 * @param dbAction Database-operation to invoke for each file
	 */
	void analyzeFolder(final Path path, final boolean recursive, final DbAction<ImageEntry> dbAction);

	List<String> getResult();

}
