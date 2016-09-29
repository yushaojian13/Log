package com.ysj.log;

public class Settings {
    private static final String TAG = "Log";

    public static final String DEFAULT_SAVE_FILENAME = ".log";

    private String tag = TAG;
    private boolean showThreadInfo = true;
    private boolean showPositionInfo = true;
    private boolean showDivider = true;
    private boolean saveToFile = false;
    private String savePath;
    private String saveFilename;
    private int methodCount = 2;
    private int methodOffset = 0;
    private @LogLevel.Level int logLevel = LogLevel.FULL;

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

    public Settings hidePositionInfo() {
        showPositionInfo = false;

        return this;
    }

    public Settings hideDivider() {
        showDivider = false;

        return this;
    }

    public Settings saveToFile(boolean save) {
        saveToFile = save;

        return this;
    }

    public Settings setSavePath(String savePath) {
        this.savePath = savePath;

        return this;
    }

    public Settings setSaveFilename(String saveFilename) {
        this.saveFilename = saveFilename;

        return this;
    }

    public Settings methodCount(int methodCount) {
        if (methodCount < 0) {
            methodCount = 0;
        }

        this.methodCount = methodCount;

        return this;
    }

    public Settings methodOffset(int offset) {
        this.methodOffset = offset;
        return this;
    }

    public Settings setLogLevel(@LogLevel.Level int  level) {
        logLevel = level;

        return this;
    }

    public String getTag() {
        return tag;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }

    public boolean isShowPositionInfo() {
        return showPositionInfo;
    }

    public boolean isShowDivider() {
        return showDivider;
    }

    public boolean isSaveToFile() {
        return saveToFile;
    }

    public String getSavePath() {
        return savePath;
    }

    public String getSaveFilename() {
        return saveFilename;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public int getMethodOffset() {
        return methodOffset;
    }

    @LogLevel.Level
    public int getLogLevel() {
        return logLevel;
    }

}
