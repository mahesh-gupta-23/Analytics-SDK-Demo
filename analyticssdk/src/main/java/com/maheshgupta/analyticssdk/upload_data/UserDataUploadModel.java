package com.maheshgupta.analyticssdk.upload_data;

import com.maheshgupta.analyticssdk.dao.AppUseTime;
import com.maheshgupta.analyticssdk.dao.OtherEvent;
import com.maheshgupta.analyticssdk.dao.user.UserDetails;
import com.maheshgupta.analyticssdk.dao.user.UserMaster;

import java.util.List;

public class UserDataUploadModel {
    private List<AppUseTime> appUseTimeList;
    private List<UserMaster> userMasterList;
    private List<UserDetails> userDetailsList;
    private List<OtherEvent> otherEventList;

    public void setAppUseTimeList(List<AppUseTime> appUseTimeList) {
        this.appUseTimeList = appUseTimeList;
    }

    public void setUserMasterList(List<UserMaster> userMasterList) {
        this.userMasterList = userMasterList;
    }

    public void setUserDetailsList(List<UserDetails> userDetailsList) {
        this.userDetailsList = userDetailsList;
    }

    public List<OtherEvent> getOtherEventList() {
        return otherEventList;
    }

    public void setOtherEventList(List<OtherEvent> otherEventList) {
        this.otherEventList = otherEventList;
    }

    public List<AppUseTime> getAppUseTimeList() {
        return appUseTimeList;
    }

    public List<UserMaster> getUserMasterList() {
        return userMasterList;
    }

    public List<UserDetails> getUserDetailsList() {
        return userDetailsList;
    }

    @Override
    public String toString() {
        return "UserDataUploadModel{" +
                "\nappUseTimeList=" + appUseTimeList +
                ",\n userMasterList=" + userMasterList +
                ",\n userDetailsList=" + userDetailsList +
                ",\n otherEventList=" + otherEventList +
                '}';
    }
}
