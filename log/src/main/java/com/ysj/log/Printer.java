package com.ysj.log;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * A wrapper of {@link Log}
 * But more pretty, simple and powerful
 */
final class Printer {
    private static final int MIN_STACK_OFFSET = 2;
    private static final int JSON_INDENT = 4;
    private static final int CHUNK_SIZE = 4000;

    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    /**
     * Localize single method count and save policy for each thread
     */
    private final ThreadLocal<Integer> localMethodCount = new ThreadLocal<>();
    private final ThreadLocal<Boolean> localSave = new ThreadLocal<>();

    private final Settings settings = new Settings();

    Settings getSettings() {
        return settings;
    }

    void t(int methodCount, boolean save) {
        localMethodCount.set(methodCount);
        localSave.set(save);
    }

    void log(@LogLevel.Level int logLevel, Object object) {
        log(settings.getTag(), logLevel, object);
    }

    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    synchronized void log(String tag, @LogLevel.Level int logLevel, Object object) {
        if (logLevel < settings.getLogLevel()) {
            return;
        }

        if (tag == null || tag.trim().length() == 0) {
            tag = settings.getTag();
        }

        if (settings.isShowDivider()) {
            logTopBorder(tag, logLevel);
            logHeaderContent(tag, logLevel, getMethodCount());
        }

        String message = createMessage(object);
        byte[] bytes = message.getBytes();
        int length = bytes.length;

        if (length <= CHUNK_SIZE) {
            logContent(tag, logLevel, message);
        } else {
            for (int i = 0; i < length; i += CHUNK_SIZE) {
                int count = Math.min(length - i, CHUNK_SIZE);
                logContent(tag, logLevel, new String(bytes, i, count));
            }
        }

        if (settings.isShowDivider()) {
            logBottomBorder(tag, logLevel);
        }
    }

    private void logTopBorder(String tag, @LogLevel.Level int logLevel) {
        logChunk(tag, logLevel, TOP_BORDER);
    }

    private void logHeaderContent(String tag, @LogLevel.Level int logLevel, int methodCount) {
        logThread(tag, logLevel);
        logMethod(tag, logLevel, methodCount);
    }

    private void logThread(String tag, @LogLevel.Level int logLevel) {
        if (settings.isShowThreadInfo()) {
            logChunk(tag, logLevel, HORIZONTAL_DOUBLE_LINE + " Thread: " + Thread.currentThread().getName());
            logDivider(tag, logLevel);
        }
    }

    private void logMethod(String tag, @LogLevel.Level int logLevel, int methodCount) {
        if (methodCount <= 0) {
            return;
        }

        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        String level = "";

        int stackOffset = getStackOffset(trace) + settings.getMethodOffset();

        //corresponding method count with the current stack may exceeds the stack trace. Trims the count
        if (methodCount + stackOffset >= trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;

            StringBuilder builder = new StringBuilder();
            builder.append("║ ")
                    .append(level)
                    .append(getSimpleClassName(trace[stackIndex].getClassName()))
                    .append(".")
                    .append(trace[stackIndex].getMethodName())
                    .append(" (")
                    .append(trace[stackIndex].getFileName())
                    .append(":")
                    .append(trace[stackIndex].getLineNumber())
                    .append(")");
            level += "   ";
            logChunk(tag, logLevel, builder.toString());
        }

        logDivider(tag, logLevel);
    }

    private void logContent(String tag, @LogLevel.Level int logLevel, String content) {
        String[] lines = content.split(System.getProperty("line.separator"));
        for (String line : lines) {
            if (settings.isShowDivider()) {
                logChunk(tag, logLevel, HORIZONTAL_DOUBLE_LINE + " " + line);
            } else {
                logChunk(tag, logLevel, line);
            }
        }
    }

    private void logBottomBorder(String tag, @LogLevel.Level int logLevel) {
        logChunk(tag, logLevel, BOTTOM_BORDER);
    }

    private void logDivider(String tag, @LogLevel.Level int logLevel) {
        logChunk(tag, logLevel, MIDDLE_BORDER);
    }

    private void logChunk(String tag, @LogLevel.Level int logLevel, String chunk) {
        switch (logLevel) {
            case LogLevel.ERROR:
                Log.e(tag, chunk);
                break;
            case LogLevel.INFO:
                Log.i(tag, chunk);
                break;
            case LogLevel.VERBOSE:
                Log.v(tag, chunk);
                break;
            case LogLevel.WARN:
                Log.w(tag, chunk);
                break;
            case LogLevel.ASSERT:
                Log.wtf(tag, chunk);
                break;
            case LogLevel.DEBUG:
            default:
                Log.d(tag, chunk);
                break;
        }

        if (isSaveToFile()) {
            saveChunk(chunk);
        }
    }

    private String createMessage(Object object) {
        StringBuilder sb = new StringBuilder();

        if (settings.isShowPositionInfo() && settings.getMethodCount() == 0) {
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();

            for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
                StackTraceElement e = trace[i];
                String name = e.getClassName();
                if (!name.equals(Printer.class.getName()) && !name.equals(L.class.getName())) {
                    sb.append(getSimpleClassName(name))
                            .append(".")
                            .append(e.getMethodName())
                            .append("(")
                            .append(e.getFileName())
                            .append(":")
                            .append(e.getLineNumber())
                            .append(")");
                    break;
                }
            }

            sb.append(" ==> ");
        }

        if (object == null) {
            sb.append("null");
        } else if (TextUtils.isEmpty(object.toString())) {
        } else if (object instanceof Throwable) {
            sb.append(Log.getStackTraceString((Throwable) object));
        } else if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            try {
                sb.append(jsonObject.toString(JSON_INDENT));
                sb.append(System.getProperty("line.separator"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            try {
                sb.append(jsonArray.toString(JSON_INDENT));
                sb.append(System.getProperty("line.separator"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (object instanceof Map) {
            sb.append(System.getProperty("line.separator"));

            Map<Object, Object> map = (Map<Object, Object>) object;
            for (Map.Entry entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append(" = ");
                sb.append(entry.getValue());
                sb.append(System.getProperty("line.separator"));
            }
        } else {
            sb.append(object.toString());
        }

        return sb.toString();
    }

    private String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    private int getMethodCount() {
        Integer count = localMethodCount.get();
        if (count != null) {
            localMethodCount.remove();
            return count;
        }

        int result = settings.getMethodCount();

        if (result < 0) {
            throw new IllegalStateException("methodCount cannot be negative");
        }

        return result;
    }

    private boolean isSaveToFile() {
        Boolean save = localSave.get();
        if (save != null) {
            return save;
        }

        return settings.isSaveToFile();
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     *
     * @param trace the stack trace
     * @return the stack offset
     */
    private int getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(Printer.class.getName()) && !name.equals(L.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    private void saveChunk(String chunk) {
        String savePath = settings.getSavePath();

        if (TextUtils.isEmpty(savePath)) {
            // TODO show a message to users
            return;
        }

        String filename = settings.getSaveFilename();
        if (TextUtils.isEmpty(filename)) {
            filename = Settings.DEFAULT_SAVE_FILENAME;
        }

        String filePath = savePath
                + System.getProperty("file.separator")
                + filename;

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean result = file.createNewFile();
                if (!result) {
                    return; // TODO show a message to users
                }
            } catch (IOException e) {
                return;
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(chunk);
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
