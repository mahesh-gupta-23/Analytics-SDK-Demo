package com.maheshgupta.analyticssdk;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;

/**
 * Created by karn on 16-10-2017.
 */

public class AnalyticsMp {
    private static AnalyticsMp instance = null;
    private static Context context;
    private static String apiKey;

    /**
     * Get instance of Analytics SDK
     *
     * @return Analytics instance
     */
    public static AnalyticsMp getInstance() {
        if (instance == null) {
            instance = new AnalyticsMp();
        }
        return instance;
    }

    /**
     * Initialize the SDK
     *
     * @param context Application context
     * @param api_key API KEY
     */
    public static void initialize(@NonNull Context context, @NonNull String api_key) {
        if (ASValidator.initializationValidator(context, api_key)) {
            AnalyticsMp.context = context;
            AnalyticsMp.apiKey = api_key;
            // TODO: 16-10-2017 Open database
        }
    }

    public static void identify(Identify identify) {

    }
}
