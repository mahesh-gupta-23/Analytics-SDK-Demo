package com.maheshgupta.analyticssdk.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String API_URL = "http://xyz.com/";
    private static Retrofit retrofit;
    private static NetworkInterface networkInterface;

    private static Retrofit getClient() {
        if (retrofit == null) {
            synchronized (ApiClient.class) {
                if (retrofit == null) {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static NetworkInterface getNetworkInterface() {
        if (networkInterface == null) {
            synchronized (ApiClient.class) {
                if (networkInterface == null) {
                    networkInterface = getClient().create(NetworkInterface.class);
                }
            }
        }
        return networkInterface;
    }
}

