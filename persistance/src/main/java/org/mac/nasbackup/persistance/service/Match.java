package org.mac.nasbackup.persistance.service;

public final class Match {
	public static final int NO_MATCH = 0;
	public static final int MATCH_ON_FILE_AND_SIZE = 1;
	public static final int MATCH_ON_FILE_AND_SIZE_AND_MAKE_AND_MODEL_AND_SOFTWARE = 2;
	public static final int FULL_MATCH_AND_SAME_DEVICE = 3;
	public static final int ERROR = -1;
}
