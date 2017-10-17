package com.maheshgupta.analyticssdk;

import android.support.annotation.NonNull;

import java.util.HashMap;


public class Identify {

    private HashMap<String, String> identityData;

    public Identify() {
        identityData = new HashMap<>();
    }

    public HashMap<String, String> getIdentityData() {
        return identityData;
    }

    public Identify set(@NonNull String key, @NonNull String value) {
        identityData.put(key, value);
        return this;
    }

    public Identify set(@NonNull String key, @NonNull int value) {
        identityData.put(key, String.valueOf(value));
        return this;
    }

    public Identify set(@NonNull String key, @NonNull double value) {
        identityData.put(key, String.valueOf(value));
        return this;
    }

    public Identify set(@NonNull String key, @NonNull float value) {
        identityData.put(key, String.valueOf(value));
        return this;
    }
}
