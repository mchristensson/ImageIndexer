package org.mac.nasbackup.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Consumer;

import org.mac.nasbackup.img.ImageData;
import org.mac.nasbackup.persistance.DbAction;
import org.mac.nasbackup.persistance.model.ImageEntry;
import org.mac.nasbackup.persistance.service.FileMetaAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.drew.imaging.ImageProcessingException;

@Repository
@Qualifier("fileAnalyzer")
public class FileMetaAnalyzerImpl implements FileMetaAnalyzer {
	
	private static final Logger logger = LoggerFactory.getLogger(FileMetaAnalyzerImpl.class);

	@Override
	public void analyzeFolder(final Path path, final boolean recursive, DbAction<ImageEntry> insertAction) {
		try {
			Files.list(path).forEach(new Consumer<Path>() {

				@Override
				public void accept(Path t) {
					
					if (Files.isDirectory(t) && recursive) {
						analyzeFolder(t, recursive, insertAction);
					} else {
						indexImage(t);
					}
				}

				private void indexImage(Path t)  {
					
						try {
							ImageEntry imageEntry =	convertToImageEntry(t);
							insertAction.perform(imageEntry);
							

						} catch (ImageProcessingException | IOException e) {
							logger.warn(e.getMessage() + " " + t.toString());
						}

				}
			});
		} catch (IOException e) {
			logger.error("Unable to process path " + path.toString() , e);
		}
	}
	
	public ImageEntry convertToImageEntry(Path t) throws ImageProcessingException, IOException {
		ImageEntry entry = new ImageEntry();
		Map<String, String> map = ImageData.getMetaDataMap(t.toFile());
		entry.setFileName(map.get(ImageData.FILE_NAME));
		entry.setFilePath(t.toFile().getPath());
		entry.setSizeFromString(map.get(ImageData.FILE_SIZE));
		entry.setMake(map.get(ImageData.MAKE));
		entry.setModel(map.get(ImageData.MODEL));
		entry.setSoftware(map.get(ImageData.SOFTWARE));
		return entry;
	}
}
