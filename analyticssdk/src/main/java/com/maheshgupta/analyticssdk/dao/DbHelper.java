package com.maheshgupta.analyticssdk.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.maheshgupta.analyticssdk.dao.user.UserDetails;
import com.maheshgupta.analyticssdk.dao.user.UserMaster;

import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "analytics.db";

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

    public void saveUserMaster(UserMaster userMaster) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(UserMaster.UserMasterRepo.TABLE_NAME, null,
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

    /**
     * Fetching List of App use time entity only 200 at a time
     *
     * @return List of AppUseTime Entity list
     */
    public List<AppUseTime> getAppUseTime() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AppUseTime.AppUseTimeRepo.TABLE_NAME,
                AppUseTime.AppUseTimeRepo.COLUMNS, null,
                null, null, null, null, "200");
        return AppUseTime.AppUseTimeRepo.getAppUseTimeList(cursor);
    }

    /**
     * Fetching all the user master list as there won't be many rows
     *
     * @return List Of UserMaster Entity
     */
    public List<UserMaster> getUserMaster() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserMaster.UserMasterRepo.TABLE_NAME,
                UserMaster.UserMasterRepo.COLUMNS, null,
                null, null, null, null, null);
        return UserMaster.UserMasterRepo.getUserMaster(cursor);
    }

    /**
     * Fetching list of user details list only 200 at a time
     *
     * @return List of User details entity
     */
    public List<UserDetails> getUserDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserDetails.UserDetailsRepo.TABLE_NAME,
                UserDetails.UserDetailsRepo.COLUMNS, null,
                null, null, null, null, "200");
        return UserDetails.UserDetailsRepo.getUserDetailsList(cursor);
    }

    /**
     * Delete uploaded AppUseTime data
     *
     * @param auto_id auto id of AppUseTime
     */
    public void deleteAppUseTime(String auto_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(AppUseTime.AppUseTimeRepo.TABLE_NAME,
                AppUseTime.AppUseTimeRepo.AUTO_ID + " = '" + auto_id + "'", null);
        db.close();
    }

    /**
     * Delete All UserMaster After upload
     */
    public void deleteAllUserMaster() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UserMaster.UserMasterRepo.TABLE_NAME, null, null);
        db.close();
    }

    /**
     * Delete uploaded UserDetails data
     *
     * @param auto_id auto id of UserDetails
     */
    public void deleteUserDetails(String auto_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UserDetails.UserDetailsRepo.TABLE_NAME,
                UserDetails.UserDetailsRepo.AUTO_ID + " = '" + auto_id + "'", null);
        db.close();
    }


}
