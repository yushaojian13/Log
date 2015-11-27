package com.ysj.sample;

import android.app.Application;

import com.ysj.log.L;

/**
 * Change L setting here.
 */
public class LOGApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        L.saveToFile(true);
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
