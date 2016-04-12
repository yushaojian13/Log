package com.ysj.sample;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.ysj.log.L;

/**
 * Change L setting here.
 */
public class LOGApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        L.saveToFile(true)
         .setSavePath(ContextCompat.getExternalFilesDirs(this, null)[0].getAbsolutePath())
         .setSaveFilename("Log");

//        L.setTag(getString(R.string.app_name)).hideThreadInfo()
//                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
        /**
         * equals
         */
//        L.setTag(getString(R.string.app_name));
//        L.hideThreadInfo();
//        L.setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
    }
}
