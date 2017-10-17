package com.maheshgupta.analyticssdkdemo;

import android.app.Application;

import com.maheshgupta.analyticssdk.AnalyticsMp;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsMp.getInstance().initialize(getApplicationContext(), "API_KEY_TEST");
        AnalyticsMp.getInstance().setUserId("TEST MAHESH USER ID");
    }
}
