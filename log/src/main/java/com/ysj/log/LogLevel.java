package com.ysj.log;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Control which level of log can be printed.
 * set NONE when release.
 */
public class LogLevel {
	public static final int	FULL	= 0;
	public static final int	VERBOSE	= 1;
	public static final int	DEBUG	= 2;
	public static final int	INFO	= 3;
	public static final int	WARN	= 4;
	public static final int	ERROR	= 5;
	public static final int	ASSERT	= 6;
	public static final int	NONE	= 7;

	@IntDef({ FULL, VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT, NONE })
	@Retention(RetentionPolicy.SOURCE)
	public @interface Level {
	}
}
