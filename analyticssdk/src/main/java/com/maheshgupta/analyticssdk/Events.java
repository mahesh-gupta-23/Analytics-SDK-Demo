package com.maheshgupta.analyticssdk;


import android.support.annotation.NonNull;

import org.json.JSONObject;

public class Events {

    private String key, value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    private void setKey(String key) {
        this.key = key;
    }

    private void setValue(String value) {
        this.value = value;
    }

    public Events(@NonNull String key, @NonNull String value) {
        this.key = key;
        this.value = value;
    }

    public Events(@NonNull String key, @NonNull int value) {
        this(key, String.valueOf(value));
    }

    public Events(@NonNull String key, @NonNull double value) {
        this(key, String.valueOf(value));
    }

    public Events(@NonNull String key, @NonNull float value) {
        this(key, String.valueOf(value));
    }

    public Events(@NonNull String key, @NonNull JSONObject value) {
        this(key, String.valueOf(value));
    }
}
