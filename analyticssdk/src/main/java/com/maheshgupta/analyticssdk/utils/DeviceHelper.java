package com.maheshgupta.analyticssdk.utils;

import android.os.Build;

public class DeviceHelper {

    public static String getDeviceMake() {
        String manufacturer = Build.MANUFACTURER;
        return capitalize(manufacturer);
    }

    public static String getDeviceModel() {
        String model = Build.MODEL;
        return capitalize(model);
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
