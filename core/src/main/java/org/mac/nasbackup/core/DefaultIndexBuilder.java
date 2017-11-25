package org.mac.nasbackup.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.mac.nasbackup.dao.GenericDao;
import org.mac.nasbackup.dao.ImageDao;
import org.mac.nasbackup.db.model.ImageEntry;
import org.mac.nasbackup.img.ImageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.drew.imaging.ImageProcessingException;

public class DefaultIndexBuilder implements IndexBuilder {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultIndexBuilder.class);
	
	private final GenericDao imgDao = new ImageDao();
	
	private Collection<Pipeline> pipelines = new LinkedList<Pipeline>();
	
	private Path destination;
	
	@Override
	public IndexBuilder addIndexPipeline(String destinationKey, Path source, boolean recursive) {
		pipelines.add(new PipelineImpl(source, recursive));
		return this;
	}

	@Override
	public IndexBuilder setDestination(Path path) {
		File f = path.toFile();
		if (!f.exists()) {
			throw new RuntimeException(
					String.format("Directory %s does not exist.", path.getFileName().toAbsolutePath().toString()));
		} else if (!f.isDirectory()) {
			throw new RuntimeException(
					String.format("Path %s is not a directory.", path.getFileName().toAbsolutePath().toString()));

		} else {
			this.destination = Paths.get(path.toUri());
			
		}
		
		return this;
	}

	@Override
	public Long startIndex(NamedParameterJdbcTemplate template) {
		imgDao.setNamedParameterJdbcTemplate(template);
		
		for (Pipeline pipeline : pipelines) {
			indexFolder(pipeline.getSource(), pipeline.isRecursive());
		}

		List<ImageEntry> resultb = imgDao.findAll();
		for (ImageEntry imageEntry : resultb) {
			logger.info(imageEntry.toString());
		}
		logger.info(String.format("NUmber b of entries in database: %s", resultb.size()));
		return (long) resultb.size();
	}
	
	@Override
	public Object transformData() {
		logger.info("About to copy data to destination {} ..." , destination);
		List<ImageEntry> resultb = imgDao.findAll();
		for (ImageEntry imageEntry : resultb) {
			logger.info(imageEntry.toString());
		}
		logger.info(String.format("NUmber b of entries in database: %s", resultb.size()));
		return null;
	}

	private void indexFolder(final Path path, final boolean recursive) {
		try {
			Files.list(path).forEach(new Consumer<Path>() {

				@Override
				public void accept(Path t) {
					
					if (Files.isDirectory(t) && recursive) {
						indexFolder(t, recursive);
					} else {
						indexImage(t);
					}
				}

				private void indexImage(Path t)  {
					
						try {
							ImageEntry entry = new ImageEntry();
							Map<String, String> map = ImageData.getMetaDataMap(t.toFile());
							entry.setFileName(map.get(ImageData.FILE_NAME));
							entry.setFilePath(t.toFile().getPath());
//							entry.setSize((int) (t.toFile().length() / 1024));
							entry.setSizeFromString(map.get(ImageData.FILE_SIZE));
							entry.setMake(map.get(ImageData.MAKE));
							entry.setModel(map.get(ImageData.MODEL));
							entry.setSoftware(map.get(ImageData.SOFTWARE));
							imgDao.insert(entry);

						} catch (ImageProcessingException | IOException e) {
							logger.warn(e.getMessage() + " " + t.toString());
						}

				}
			});
		} catch (IOException e) {
			logger.error("Unable to process path " + path.toString() , e);
		}
	}
	
	
}
	