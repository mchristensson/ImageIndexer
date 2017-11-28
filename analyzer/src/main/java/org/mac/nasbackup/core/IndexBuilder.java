package org.mac.nasbackup.core;

import java.nio.file.Path;

@Deprecated
public interface IndexBuilder {

	public IndexBuilder addIndexPipeline(String destinationKey, Path source, boolean recursive);

	public IndexBuilder setDestination(Path path);

	public Long startIndex();

	Object transformData();

}