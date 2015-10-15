package com.ysj.log;

/**
 * Control which level of log can be printed.
 * set NONE when release.
 */
public enum  LogLevel {
    FULL,
    ASSERT,
    ERROR,
    WARN,
    INFO,
    DEBUG,
    VERBOSE,
    NONE
}
