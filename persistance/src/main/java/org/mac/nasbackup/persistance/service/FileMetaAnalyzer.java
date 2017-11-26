package org.mac.nasbackup.persistance.service;

import java.nio.file.Path;

import org.mac.nasbackup.persistance.DbAction;
import org.mac.nasbackup.persistance.model.ImageEntry;


public interface FileMetaAnalyzer {

	void analyzeFolder(final Path path, final boolean recursive, DbAction<ImageEntry> insertAction);

}
