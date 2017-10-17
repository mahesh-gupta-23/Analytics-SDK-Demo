package com.maheshgupta.analyticssdk.jobs;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.maheshgupta.analyticssdk.dao.DbHelper;
import com.maheshgupta.analyticssdk.network.ApiClient;
import com.maheshgupta.analyticssdk.upload_data.UploadDataResponse;
import com.maheshgupta.analyticssdk.upload_data.UserDataUploadModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class UploadDataJob extends Job {
    public static final String TAG = "UploadDataJob";
    private DbHelper dbHelper;

    public static void scheduleJob() {
        new JobRequest.Builder(UploadDataJob.TAG)
                .setExact(1000L)
                .build()
                .schedule();
    }

    public static void schedulePeriodicJob() {
        int jobId = new JobRequest.Builder(UploadDataJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .build()
                .schedule();
    }


    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        dbHelper = new DbHelper(getContext());
        UserDataUploadModel userDataUploadModel = new UserDataUploadModel();

        userDataUploadModel.setAppUseTimeList(dbHelper.getAppUseTime());
        userDataUploadModel.setUserMasterList(dbHelper.getUserMaster());
        userDataUploadModel.setUserDetailsList(dbHelper.getUserDetails());

        if (userDataUploadModel.getAppUseTimeList().size() > 0 ||
                userDataUploadModel.getUserMasterList().size() > 0 ||
                userDataUploadModel.getUserDetailsList().size() > 0) {
            return uploadDataToServer(userDataUploadModel, getApiKey(getContext()));
        } else {
            return Result.SUCCESS;
        }
    }

    private Result uploadDataToServer(UserDataUploadModel uploadDataJson, final String api_key) {
        try {
            Call<UploadDataResponse> call = ApiClient.getNetworkInterface(api_key).
                    uploadUserData(uploadDataJson);
            Response<UploadDataResponse> response = call.execute();
            List<String> appUseTimeIdList = response.body().getAppUseTimeIdList();
            List<String> userDetailsIdList = response.body().getUserDetailsIdList();
            List<String> otherEventIdList = response.body().getOtherEventIdList();

            //Delete the data after uploading
            dbHelper.deleteAllUserMaster();
            for (int i = 0; i < appUseTimeIdList.size(); i++) {
                dbHelper.deleteAppUseTime(appUseTimeIdList.get(i));
            }

            for (int i = 0; i < userDetailsIdList.size(); i++) {
                dbHelper.deleteAppUseTime(userDetailsIdList.get(i));
            }

            for (int i = 0; i < otherEventIdList.size(); i++) {
                dbHelper.deleteOtherEvent(otherEventIdList.get(i));
            }
            return Result.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return Result.FAILURE;
        }
    }

    /**
     * Get the api_key from sharedPreferences
     *
     * @param context Context
     * @return api_key String
     */
    private String getApiKey(Context context) {
        SharedPreferences preferences =
                context.getSharedPreferences("analytics_sdk_prefs", Context.MODE_PRIVATE);
        return preferences.getString("api_key", null);
    }

}
