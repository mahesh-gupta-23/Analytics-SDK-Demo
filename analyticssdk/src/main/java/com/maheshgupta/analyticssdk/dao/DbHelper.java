package com.maheshgupta.analyticssdk.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.maheshgupta.analyticssdk.dao.user.UserDetails;
import com.maheshgupta.analyticssdk.dao.user.UserMaster;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "analytics.db";
    private SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserMaster.UserMasterRepo.CREATE_TABLE);
        sqLiteDatabase.execSQL(UserDetails.UserDetailsRepo.CREATE_TABLE);
        sqLiteDatabase.execSQL(AppUseTime.AppUseTimeRepo.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserMaster.UserMasterRepo.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserDetails.UserDetailsRepo.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppUseTime.AppUseTimeRepo.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /*public void openDB() {
        db = this.getWritableDatabase();
    }

    public void closeDB() {
        db.close();
    }*/

    public void saveUserMaster(UserMaster userMaster) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insertedId = db.insert(UserMaster.UserMasterRepo.TABLE_NAME, null,
                UserMaster.UserMasterRepo.getContentValues(userMaster));
        db.close();
    }

    public void saveUserDetails(UserDetails userDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(UserDetails.UserDetailsRepo.TABLE_NAME, null,
                UserDetails.UserDetailsRepo.getContentValues(userDetails));
        db.close();
    }


    public void saveAppStartTime(AppUseTime appUseTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(AppUseTime.AppUseTimeRepo.TABLE_NAME, null,
                AppUseTime.AppUseTimeRepo.getContentValues(appUseTime));
        db.close();
    }
}
