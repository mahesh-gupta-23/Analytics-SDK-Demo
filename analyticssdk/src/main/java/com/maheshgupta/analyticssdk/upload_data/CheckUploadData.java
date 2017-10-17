package com.maheshgupta.analyticssdk.upload_data;

import android.content.Context;
import android.util.Log;

import com.maheshgupta.analyticssdk.dao.DbHelper;
import com.maheshgupta.analyticssdk.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckUploadData {
    private DbHelper dbHelper;
    private static CheckUploadData instance;

    private CheckUploadData(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static CheckUploadData getInstance(Context context) {
        if (instance == null)
            instance = new CheckUploadData(context);
        return instance;
    }

    public void checkPendingUpload(String api_key) {
        UserDataUploadModel userDataUploadModel = new UserDataUploadModel();

        userDataUploadModel.setAppUseTimeList(dbHelper.getAppUseTime());
        userDataUploadModel.setUserMasterList(dbHelper.getUserMaster());
        userDataUploadModel.setUserDetailsList(dbHelper.getUserDetails());

        if (userDataUploadModel.getAppUseTimeList().size() > 0 ||
                userDataUploadModel.getUserMasterList().size() > 0 ||
                userDataUploadModel.getUserDetailsList().size() > 0)
            uploadDataToServer(userDataUploadModel, api_key);
    }

    private void uploadDataToServer(UserDataUploadModel uploadDataJson, final String api_key) {
        Call<UploadDataResponse> call = ApiClient.getNetworkInterface(api_key).
                uploadUserData(uploadDataJson);
        call.enqueue(new Callback<UploadDataResponse>() {
            @Override
            public void onResponse(Call<UploadDataResponse> call, Response<UploadDataResponse> response) {
                List<String> appUseTimeIdList = response.body().getAppUseTimeIdList();
                List<String> userDetailsIdList = response.body().getUserDetailsIdList();

                //Delete the data after uploading
                dbHelper.deleteAllUserMaster();
                for (int i = 0; i < appUseTimeIdList.size(); i++) {
                    dbHelper.deleteAppUseTime(appUseTimeIdList.get(i));
                }

                for (int i = 0; i < userDetailsIdList.size(); i++) {
                    dbHelper.deleteAppUseTime(userDetailsIdList.get(i));
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
            }
        });
    }
}
