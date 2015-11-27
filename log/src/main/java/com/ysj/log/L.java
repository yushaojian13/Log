package com.ysj.log;

/**
 * L is a wrapper of {@link android.util.Log}
 * But more convenient
 */
public class L {

    private static final Printer printer = new Printer();

    private L() {
    }

    public static Settings setTag(String tag) {
        return printer.getSettings().setTag(tag);
    }

    public static Settings hideThreadInfo() {
        return printer.getSettings().hideThreadInfo();
    }

    public static Settings hidePositionInfo() {
        return printer.getSettings().hidePositionInfo();
    }

    public static Settings hideDivider() {
        return printer.getSettings().hideDivider();
    }

    public static Settings saveToFile(boolean save) {
        return printer.getSettings().saveToFile(save);
    }

    public static Settings methodCount(int methodCount) {
        return printer.getSettings().methodCount(methodCount);
    }

    public static Settings methodOffset(int offset) {
        return printer.getSettings().methodOffset(offset);
    }

    public static Settings setLogLevel(LogLevel level) {
        return printer.getSettings().setLogLevel(level);
    }

    public static void v() {
        printer.log(LogLevel.VERBOSE, "");
    }

    public static void d() {
        printer.log(LogLevel.DEBUG, "");
    }

    public static void i() {
        printer.log(LogLevel.INFO, "");
    }

    public static void w() {
        printer.log(LogLevel.WARN, "");
    }

    public static void e() {
        printer.log(LogLevel.ERROR, "");
    }

    public static void v(Object msg) {
        printer.log(LogLevel.VERBOSE, msg);
    }

    public static void d(Object msg) {
        printer.log(LogLevel.DEBUG, msg);
    }

    public static void i(Object msg) {
        printer.log(LogLevel.INFO, msg);
    }

    public static void w(Object msg) {
        printer.log(LogLevel.WARN, msg);
    }

    public static void e(Object msg) {
        printer.log(LogLevel.ERROR, msg);
    }

    public static void v(String tag, Object msg) {
        printer.log(tag, LogLevel.VERBOSE, msg);
    }

    public static void d(String tag, Object msg) {
        printer.log(tag, LogLevel.DEBUG, msg);
    }

    public static void i(String tag, Object msg) {
        printer.log(tag, LogLevel.INFO, msg);
    }

    public static void w(String tag, Object msg) {
        printer.log(tag, LogLevel.WARN, msg);
    }

    public static void e(String tag, Object msg) {
        printer.log(tag, LogLevel.ERROR, msg);
    }

}
