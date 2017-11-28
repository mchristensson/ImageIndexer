package org.mac.nasbackup.core;

import java.nio.file.Path;
@Deprecated
public class PipelineImpl implements Pipeline {
	private Path source;
	private boolean recursive;
	
	public PipelineImpl(Path source, boolean recursive) {
		this.source = source;
		this.recursive = recursive;
	}

	@Override
	public boolean isRecursive() {
		return recursive;
	}


	@Override
	public Path getSource() {
		return source;
	}

	
}