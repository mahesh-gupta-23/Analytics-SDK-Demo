package com.maheshgupta.analyticssdk.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class OtherEvent {
    private int auto_id;
    private String user_id;
    private String event_key;
    private String event_value;

    public OtherEvent(String user_id, String event_key, String event_value) {
        this.user_id = user_id;
        this.event_key = event_key;
        this.event_value = event_value;
    }

    public OtherEvent(int auto_id, String user_id, String event_key, String event_value) {
        this.auto_id = auto_id;
        this.user_id = user_id;
        this.event_key = event_key;
        this.event_value = event_value;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getEvent_key() {
        return event_key;
    }

    public String getEvent_value() {
        return event_value;
    }

    @Override
    public String toString() {
        return "OtherEvent{" +
                "\nauto_id=" + auto_id +
                "\n user_id=" + user_id +
                ",\n event_key='" + event_key + '\'' +
                ",\n event_value='" + event_value + '\'' +
                '}';
    }

    public static class OtherEventRepo {
        public static final String TABLE_NAME = "other_event";
        public static final String AUTO_ID = "auto_id";
        public static final String USER_ID = "useR_id";
        public static final String EVENT_KEY = "event_key";
        public static final String EVENT_VALUE = "event_value";

        public static final String[] COLUMNS = new String[]{OtherEventRepo.AUTO_ID,
                OtherEventRepo.USER_ID, OtherEventRepo.EVENT_KEY, OtherEventRepo.EVENT_VALUE};

        public static final String CREATE_TABLE = "CREATE TABLE " + OtherEventRepo.TABLE_NAME + "("
                + OtherEventRepo.AUTO_ID + " integer primary key autoincrement, "
                + OtherEventRepo.USER_ID + " TEXT, " + OtherEventRepo.EVENT_KEY + " TEXT, "
                + OtherEventRepo.EVENT_VALUE + " TEXT "
                + ")";

        public static ContentValues getContentValues(OtherEvent otherEvent) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(OtherEventRepo.USER_ID, otherEvent.getUser_id());
            contentValues.put(OtherEventRepo.EVENT_KEY, otherEvent.getEvent_key());
            contentValues.put(OtherEventRepo.EVENT_VALUE, otherEvent.getEvent_value());
            return contentValues;
        }

        public static List<OtherEvent> getOtherEventList(Cursor cursor) {
            if (cursor == null || cursor.getCount() <= 0)
                return null;

            List<OtherEvent> otherEventList = new ArrayList<>();
            while (cursor.moveToNext()) {
                otherEventList.add(new OtherEvent(
                        cursor.getInt(cursor.getColumnIndex(OtherEventRepo.AUTO_ID)),
                        cursor.getString(cursor.getColumnIndex(OtherEventRepo.USER_ID)),
                        cursor.getString(cursor.getColumnIndex(OtherEventRepo.EVENT_KEY)),
                        cursor.getString(cursor.getColumnIndex(OtherEventRepo.EVENT_VALUE))
                ));
            }
            cursor.close();
            return otherEventList;
        }
    }
}
