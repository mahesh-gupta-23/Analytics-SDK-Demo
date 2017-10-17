package com.maheshgupta.analyticssdk.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class AppUseTime {

    private int auto_id;
    private String start_time;

    public AppUseTime(String start_time) {
        this.start_time = start_time;
    }

    public AppUseTime(int auto_id, String start_time) {
        this.auto_id = auto_id;
        this.start_time = start_time;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public String getStart_time() {
        return start_time;
    }

    @Override
    public String toString() {
        return "AppUseTime{" +
                "\nauto_id=" + auto_id +
                ",\n start_time='" + start_time + '\'' +
                '}';
    }

    public static class AppUseTimeRepo {
        public static final String TABLE_NAME = "app_use_time";
        public static final String AUTO_ID = "auto_id";
        public static final String START_TIME = "start_time";

        public static final String[] COLUMNS = new String[]{AppUseTimeRepo.AUTO_ID,
                AppUseTimeRepo.START_TIME};

        public static final String CREATE_TABLE = "CREATE TABLE " + AppUseTimeRepo.TABLE_NAME + "("
                + AppUseTimeRepo.AUTO_ID + " integer primary key autoincrement, " + AppUseTimeRepo.START_TIME + " TEXT"
                + ")";

        public static ContentValues getContentValues(AppUseTime appUseTime) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(AppUseTimeRepo.START_TIME, appUseTime.getStart_time());
            return contentValues;
        }

        public static List<AppUseTime> getAppUseTimeList(Cursor cursor) {
            if (cursor == null || cursor.getCount() <= 0)
                return null;

            List<AppUseTime> appUseTimeList = new ArrayList<>();
            while (cursor.moveToNext()) {
                appUseTimeList.add(new AppUseTime(
                        cursor.getInt(cursor.getColumnIndex(AppUseTimeRepo.AUTO_ID)),
                        cursor.getString(cursor.getColumnIndex(AppUseTimeRepo.START_TIME))
                ));
            }
            cursor.close();
            return appUseTimeList;
        }
    }
}
