package org.mac.nasbackup.core;

import java.nio.file.Path;

public interface Pipeline {

	Path getSource();

	boolean isRecursive();

}
