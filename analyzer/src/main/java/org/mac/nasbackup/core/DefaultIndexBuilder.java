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

import org.mac.nasbackup.img.ImageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.imaging.ImageProcessingException;

import ch.qos.logback.core.joran.spi.NoAutoStart;

@Deprecated
public class DefaultIndexBuilder implements IndexBuilder {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultIndexBuilder.class);
	
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
	public Long startIndex() {
		/*
		for (Pipeline pipeline : pipelines) {
			indexFolder(pipeline.getSource(), pipeline.isRecursive());
		}

		List<ImageEntry> resultb = imgDao.findAll();
		for (ImageEntry imageEntry : resultb) {
			logger.info(imageEntry.toString());
		}
		logger.info(String.format("NUmber b of entries in database: %s", resultb.size()));
		return (long) resultb.size();
		*/
		return null;
	}
	
	@Override
	public Object transformData() {
		logger.info("About to copy data to destination {} ..." , destination);
		/*
		List<ImageEntry> resultb = imgDao.findAll();
		for (ImageEntry imageEntry : resultb) {
			logger.info(imageEntry.toString());
		}
		logger.info(String.format("NUmber b of entries in database: %s", resultb.size()));
		*/
		return null;
	}

	
	
	
}
	