package com.maheshgupta.analyticssdk.upload_data;

import java.util.ArrayList;
import java.util.List;

public class UploadDataResponse {
    private List<String> appUseTimeIdList = new ArrayList<>();
    private List<String> userDetailsIdList = new ArrayList<>();
    private List<String> otherEventIdList = new ArrayList<>();

    public List<String> getAppUseTimeIdList() {
        return appUseTimeIdList;
    }

    public List<String> getUserDetailsIdList() {
        return userDetailsIdList;
    }

    public List<String> getOtherEventIdList() {
        return otherEventIdList;
    }

    @Override
    public String toString() {
        return "UploadDataResponse{" +
                "\nappUseTimeIdList=" + appUseTimeIdList +
                ",\n userDetailsIdList=" + userDetailsIdList +
                ",\n otherEventIdList=" + otherEventIdList +
                '}';
    }
}
