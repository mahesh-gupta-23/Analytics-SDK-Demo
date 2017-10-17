package com.maheshgupta.analyticssdk.dao.user;

import android.content.ContentValues;
import android.database.Cursor;

public class UserMaster {
    private String user_id;
    private String os_version;
    private String application_version;
    private String device_make;
    private String device_model;
    private String os;
    private String upload_flag;
    private String time_stamp;

    public UserMaster(String user_id, String os_version, String application_version,
                      String device_make, String device_model, String os, String upload_flag,
                      String time_stamp) {
        this.user_id = user_id;
        this.os_version = os_version;
        this.application_version = application_version;
        this.device_make = device_make;
        this.device_model = device_model;
        this.os = os;
        this.upload_flag = upload_flag;
        this.time_stamp = time_stamp;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getOs_version() {
        return os_version;
    }

    public String getApplication_version() {
        return application_version;
    }

    public String getDevice_make() {
        return device_make;
    }

    public String getDevice_model() {
        return device_model;
    }

    public String getOs() {
        return os;
    }

    public String getUpload_flag() {
        return upload_flag;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    @Override
    public String toString() {
        return "UserMaster{" +
                "\nuser_id='" + user_id + '\'' +
                ",\n os_version='" + os_version + '\'' +
                ",\n application_version='" + application_version + '\'' +
                ",\n device_make='" + device_make + '\'' +
                ",\n device_model='" + device_model + '\'' +
                ",\n os='" + os + '\'' +
                ",\n upload_flag=" + upload_flag +
                ",\n time_stamp='" + time_stamp + '\'' +
                '}';
    }

    public static class UserMasterRepo {
        public static final String TABLE_NAME = "user_master";
        public static final String USER_ID = "user_id";
        public static final String OS_VERSION = "os_version";
        public static final String APPLICATION_VERSION = "application_version";
        public static final String DEVICE_MAKE = "device_make";
        public static final String DEVICE_MODEL = "device_model";
        public static final String OS = "os";
        public static final String UPLOAD_FLAG = "upload_flag";
        public static final String TIME_STAMP = "time_stamp";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + USER_ID + " TEXT, " + OS_VERSION + " TEXT , "
                + APPLICATION_VERSION + " TEXT, " + DEVICE_MAKE + " INTEGER, "
                + DEVICE_MODEL + " TEXT, " + OS + " TEXT, " + UPLOAD_FLAG + " TEXT, " + TIME_STAMP + " TEXT"
                + ")";

        public static ContentValues getContentValues(UserMaster userMaster) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_ID, userMaster.getUser_id());
            contentValues.put(OS_VERSION, userMaster.getOs_version());
            contentValues.put(APPLICATION_VERSION, userMaster.getApplication_version());
            contentValues.put(DEVICE_MAKE, userMaster.getDevice_make());
            contentValues.put(DEVICE_MODEL, userMaster.getDevice_model());
            contentValues.put(OS, userMaster.getOs());
            contentValues.put(UPLOAD_FLAG, userMaster.getUpload_flag());
            contentValues.put(TIME_STAMP, userMaster.getTime_stamp());
            return contentValues;
        }

        public static UserMaster getUserMaster(Cursor cursor) {
            if (cursor == null || cursor.getCount() <= 0)
                return null;

            cursor.moveToFirst();
            UserMaster userMaster = new UserMaster(
                    cursor.getString(cursor.getColumnIndex(USER_ID)),
                    cursor.getString(cursor.getColumnIndex(OS_VERSION)),
                    cursor.getString(cursor.getColumnIndex(APPLICATION_VERSION)),
                    cursor.getString(cursor.getColumnIndex(DEVICE_MAKE)),
                    cursor.getString(cursor.getColumnIndex(DEVICE_MODEL)),
                    cursor.getString(cursor.getColumnIndex(OS)),
                    cursor.getString(cursor.getColumnIndex(UPLOAD_FLAG)),
                    cursor.getString(cursor.getColumnIndex(TIME_STAMP))
            );
            cursor.close();
            return userMaster;
        }
    }
}
