package com.ysj.log;

public class Settings {
    private static final String TAG = "Log";

    private String tag = TAG;

    private boolean showThreadInfo = true;

    private LogLevel logLevel = LogLevel.FULL;

    public Settings setTag(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag may not be null");
        }
        if (tag.trim().length() == 0) {
            throw new IllegalStateException("tag may not be empty");
        }

        this.tag = tag;

        return this;
    }

    public Settings hideThreadInfo() {
        showThreadInfo = false;

        return this;
    }

    public Settings setLogLevel(LogLevel level) {
        logLevel = level;

        return this;
    }

    public String getTag() {
        return tag;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

}
