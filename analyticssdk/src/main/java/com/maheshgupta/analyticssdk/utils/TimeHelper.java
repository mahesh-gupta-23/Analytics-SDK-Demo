package com.maheshgupta.analyticssdk.utils;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getTime() {
        return (String) DateFormat.format("hh:mm aaa", Calendar.getInstance().getTime());
    }

    public static String getTimeHourAndMinIn24() {
        return (String) DateFormat.format("HH:mm", Calendar.getInstance().getTime());
    }

    public static String getTimeIn24() {
        return (String) DateFormat.format("HH:mm:ss", Calendar.getInstance().getTime());
    }

    public static String getTimeStamp() {
        return (String) (DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
    }
}
