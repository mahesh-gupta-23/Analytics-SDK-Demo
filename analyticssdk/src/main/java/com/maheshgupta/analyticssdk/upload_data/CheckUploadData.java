package com.maheshgupta.analyticssdk.upload_data;

import android.content.Context;
import android.util.Log;

import com.evernote.android.job.Job;
import com.maheshgupta.analyticssdk.dao.DbHelper;
import com.maheshgupta.analyticssdk.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckUploadData {
    private DbHelper dbHelper;
    private static CheckUploadData instance;
    private UploadListener listener;

    public interface UploadListener {
        Job.Result onUploaded();

        Job.Result onError();
    }

    public void setUploadListener(UploadListener listener) {
        this.listener = listener;
    }

    private CheckUploadData(Context context, UploadListener listener) {
        dbHelper = new DbHelper(context);
    }

    public static CheckUploadData getInstance(Context context, UploadListener listner) {
        if (instance == null)
            instance = new CheckUploadData(context, listner);
        return instance;
    }

    public void checkPendingUpload(String api_key) {
        Log.d("AnSDKDemo", "Upload called");
        UserDataUploadModel userDataUploadModel = new UserDataUploadModel();

        userDataUploadModel.setAppUseTimeList(dbHelper.getAppUseTime());
        userDataUploadModel.setUserMasterList(dbHelper.getUserMaster());
        userDataUploadModel.setUserDetailsList(dbHelper.getUserDetails());

        if (userDataUploadModel.getAppUseTimeList().size() > 0 ||
                userDataUploadModel.getUserMasterList().size() > 0 ||
                userDataUploadModel.getUserDetailsList().size() > 0)
            uploadDataToServer(userDataUploadModel, api_key);
        else listener.onUploaded();
    }

    private void uploadDataToServer(UserDataUploadModel uploadDataJson, final String api_key) {
        Call<UploadDataResponse> call = ApiClient.getNetworkInterface(api_key).
                uploadUserData(uploadDataJson);
        call.enqueue(new Callback<UploadDataResponse>() {
            @Override
            public void onResponse(Call<UploadDataResponse> call, Response<UploadDataResponse> response) {
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

                //Check if some more data need to be uploaded
                checkPendingUpload(api_key);
            }

            @Override
            public void onFailure(Call<UploadDataResponse> call, Throwable t) {
                t.printStackTrace();
                if (call.isCanceled()) {
                    Log.d("AnSDKDemo", "No internet connection");
                }

                listener.onError();
            }
        });
    }
}
