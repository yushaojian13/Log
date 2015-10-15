package com.ysj.sample;

import android.app.Application;

/**
 * Change L setting here.
 */
public class LOGApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

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
