package com.maheshgupta.analyticssdk.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private static SharedPreferences preferences = null;
    private static Context context;

    private static String user_id = null;
    private static String app_version = null;
    private static String os_version = null;
    private static String api_key = null;

    public PreferencesHelper(Context context) {
        preferences = context.getSharedPreferences("analytics_sdk_prefs", Context.MODE_PRIVATE);
    }

    public static void loadPreferences(Context context) {
        if (preferences == null) {
            new PreferencesHelper(context);
            PreferencesHelper.context = context;
        }
        app_version = preferences.getString("app_version", null);
        os_version = preferences.getString("os_version", null);
        user_id = preferences.getString("user_id", null);
        api_key = preferences.getString("api_key", null);
    }

    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        PreferencesHelper.user_id = user_id;
        preferences.edit().putString("user_id", user_id).apply();
    }

    public static String getApi_key() {
        return api_key;
    }

    public static void setApi_key(String api_key) {
        PreferencesHelper.api_key = api_key;
        preferences.edit().putString("api_key", api_key).apply();
    }

    public static String getApp_version() {
        return app_version;
    }

    public static void setApp_version(String app_version) {
        PreferencesHelper.app_version = app_version;
        preferences.edit().putString("app_version", app_version).apply();

    }

    public static String getOs_version() {
        return os_version;
    }

    public static void setOs_version(String os_version) {
        PreferencesHelper.os_version = os_version;
        preferences.edit().putString("os_version", os_version).apply();
    }

    public static void clearPrefs() {
        preferences.edit().putString("user", null).apply();
        preferences.edit().putString("api_key", null).apply();
        preferences.edit().putString("app_version", null).apply();
        preferences.edit().putString("os_version", null).apply();
        loadPreferences(context);
    }
}
