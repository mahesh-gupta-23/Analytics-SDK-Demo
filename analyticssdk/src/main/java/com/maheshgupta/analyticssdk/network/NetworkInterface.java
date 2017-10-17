package com.maheshgupta.analyticssdk.network;


import com.maheshgupta.analyticssdk.UserDataUploadModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkInterface {

    @POST("uploadUserData.php")
    Call<List<String>> uploadUserData(@Body UserDataUploadModel userDataUploadModel);

}