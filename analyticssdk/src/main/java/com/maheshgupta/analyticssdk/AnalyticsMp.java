package com.maheshgupta.analyticssdk;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.evernote.android.job.JobManager;
import com.maheshgupta.analyticssdk.dao.AppUseTime;
import com.maheshgupta.analyticssdk.dao.DbHelper;
import com.maheshgupta.analyticssdk.dao.OtherEvent;
import com.maheshgupta.analyticssdk.dao.user.UserDetails;
import com.maheshgupta.analyticssdk.dao.user.UserMaster;
import com.maheshgupta.analyticssdk.jobs.AnalyticsJobCreator;
import com.maheshgupta.analyticssdk.jobs.UploadDataJob;
import com.maheshgupta.analyticssdk.utils.ASValidator;
import com.maheshgupta.analyticssdk.utils.DeviceHelper;
import com.maheshgupta.analyticssdk.utils.PreferencesHelper;
import com.maheshgupta.analyticssdk.utils.TimeHelper;

import java.lang.reflect.Field;

public class AnalyticsMp {
    private static AnalyticsMp instance = null;
    private Context context;
    private String apiKey;
    private DbHelper dbHelper;

    /**
     * Get instance of Analytics SDK
     *
     * @return Analytics instance
     */
    public static AnalyticsMp getInstance() {
        if (instance == null) {
            synchronized (AnalyticsMp.class) {
                instance = new AnalyticsMp();
            }
        }
        return instance;
    }

    /**
     * Initialize the SDK
     *
     * @param context Application context
     * @param api_key API KEY
     */
    public void initialize(@NonNull Context context, @NonNull String api_key) {
        if (ASValidator.initializationValidator(context, api_key)) {
            this.context = context;
            this.apiKey = api_key;
            if (dbHelper == null) {
                dbHelper = new DbHelper(context);
                //dbHelper.openDB();
            }
            PreferencesHelper.loadPreferences(context);
            PreferencesHelper.setApi_key(api_key);

            //Set the start time of the application
            dbHelper.saveAppStartTime(new AppUseTime(
                    TimeHelper.getTimeStamp()
            ));

            JobManager.create(context).addJobCreator(new AnalyticsJobCreator());

            UploadDataJob.schedulePeriodicJob();
        }
    }

    public void setUserId(String userId) {
        if (ASValidator.userIdValidator(userId)) {
            String versionCode = String.valueOf(BuildConfig.VERSION_CODE);
            String osVersion = String.valueOf(Build.VERSION.RELEASE);
            //Check if this user_id already exist and if app or os version is changed or not
            if (PreferencesHelper.getUser_id() == null ||
                    !PreferencesHelper.getUser_id().equals(userId) ||
                    PreferencesHelper.getApp_version() == null ||
                    !PreferencesHelper.getApp_version().equals(versionCode) ||
                    PreferencesHelper.getOs_version() == null ||
                    !PreferencesHelper.getOs_version().equals(osVersion)) {
                dbHelper.saveUserMaster(new UserMaster(
                        userId,
                        osVersion,
                        versionCode,
                        DeviceHelper.getDeviceMake(),
                        DeviceHelper.getDeviceModel(),
                        getOsName(),
                        TimeHelper.getTimeStamp()
                ));

                PreferencesHelper.setUser_id(userId);
                PreferencesHelper.setApp_version(versionCode);
            }
        }
    }

    public void identify(Identify identify) {
        for (String key : identify.getIdentityData().keySet()) {
            dbHelper.saveUserDetails(new UserDetails(PreferencesHelper.getUser_id(), key,
                    identify.getIdentityData().get(key), TimeHelper.getTimeStamp()));
        }
    }

    public void saveEvents(Events events) {
        dbHelper.saveOtherEvent(new OtherEvent(PreferencesHelper.getUser_id(),
                events.getKey(), events.getValue()));
    }

    private String getOsName() {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        String osName = null;
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                osName = fieldName;
            }
        }
        return osName;
    }
}
