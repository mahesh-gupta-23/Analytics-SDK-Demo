package com.maheshgupta.analyticssdk.network;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String API_URL = "http://xyz.com/";
    private static Retrofit retrofit;
    private static NetworkInterface networkInterface;

    private static Retrofit getClient(final String api_key) {
        if (retrofit == null) {
            synchronized (ApiClient.class) {
                if (retrofit == null) {
                    //Adding api key to every request as header
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request original = chain.request();
                                    Request request = original.newBuilder()
                                            .header("api_key", api_key)
                                            .method(original.method(), original.body())
                                            .build();
                                    return chain.proceed(request);
                                }
                            })
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

    public static NetworkInterface getNetworkInterface(String api_key) {
        if (networkInterface == null) {
            synchronized (ApiClient.class) {
                if (networkInterface == null) {
                    networkInterface = getClient(api_key).create(NetworkInterface.class);
                }
            }
        }
        return networkInterface;
    }
}

