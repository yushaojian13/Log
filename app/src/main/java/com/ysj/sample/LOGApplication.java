package com.ysj.sample;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.ysj.log.L;
import com.ysj.log.LogLevel;


/**
 * Change L setting here.
 */
public class LOGApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 全局设置
        L.setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE) // 控制日志级别，默认为LogLevel.FULL
                .setTag("GlobalTag"); // 设置日志tag，默认为“Log”
//        .hideThreadInfo() // 隐藏线程信息，默认显示
//        .hidePositionInfo() // 隐藏位置信息，默认显示
//        .hideDivider() // 隐藏分隔条，默认显示
//        .methodCount(5) // 设置调用堆栈数，默认为2
//        .methodOffset(1) // 设置调用堆栈偏移量，默认为0
//                .saveToFile(true) // 保存日志到文件，默认不保存，如需保存，须同时设置savePath和saveFilename
//                .setSavePath(ContextCompat.getExternalFilesDirs(this, null)[0].getAbsolutePath()) // 日志文件路径，默认为空，不保存
//                .setSaveFilename("Log"); // 日志文件名，默认为空，不保存

        // 单条设置
        // L.t(true);
        // L.methodCount(10);
        // L.methodOffset(2);
    }
}