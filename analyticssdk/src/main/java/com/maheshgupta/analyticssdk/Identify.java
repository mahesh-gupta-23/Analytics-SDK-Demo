package com.maheshgupta.analyticssdk;

import android.support.annotation.NonNull;

public class Identify {


    private String key, value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void set(@NonNull String key, @NonNull String value) {
        this.key = key;
        this.value = value;
    }

    public void set(@NonNull String key, @NonNull int value) {
        this.key = key;
        this.value = String.valueOf(value);
    }

    public void set(@NonNull String key, @NonNull double value) {
        this.key = key;
        this.value = String.valueOf(value);
    }

    public void set(@NonNull String key, @NonNull float value) {
        this.key = key;
        this.value = String.valueOf(value);
    }

}
