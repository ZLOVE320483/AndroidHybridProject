package com.zlove.hybridproject.base;

import android.app.Application;
import android.util.Log;

/**
 * Created by ZLOVE on 2016/11/16.
 */
public class HybridApplication extends Application {

    private static final String LOG_TAG = HybridApplication.class.getSimpleName();
    private static HybridApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static HybridApplication getInstance() {
        if (instance == null)
            Log.e(LOG_TAG, "application is null");
        return instance;
    }
}
