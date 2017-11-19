package org.mac.nasbackup.core;

import java.nio.file.Path;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface IndexBuilder {

	public IndexBuilder addIndexPipeline(String destinationKey, Path source, boolean recursive);

	public IndexBuilder setDestination(Path path);

	public Long startIndex(NamedParameterJdbcTemplate template);

	Object transformData();

}