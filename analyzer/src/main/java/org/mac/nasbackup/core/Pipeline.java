package org.mac.nasbackup.core;

import java.nio.file.Path;
@Deprecated
public interface Pipeline {

	Path getSource();

	boolean isRecursive();

}
