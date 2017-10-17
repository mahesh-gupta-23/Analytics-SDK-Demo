package com.maheshgupta.analyticssdk.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.maheshgupta.analyticssdk.jobs.UploadDataJob;
import com.maheshgupta.analyticssdk.upload_data.CheckUploadData;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = NetworkUtil.getConnectivityStatusString(context);
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            //Check if there is active internet connection
            if (status != NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                UploadDataJob.scheduleJob();
            }
        }
    }
}
