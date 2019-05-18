package com.maheshgupta.analyticssdk.network;


import com.maheshgupta.analyticssdk.upload_data.UploadDataResponse;
import com.maheshgupta.analyticssdk.upload_data.UserDataUploadModel;
import com.maheshgupta.analyticssdk.utils.PreferencesHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NetworkInterface {

    @POST("uploadUserData.php")
    Call<UploadDataResponse> uploadUserData(@Body UserDataUploadModel userDataUploadModel);

}