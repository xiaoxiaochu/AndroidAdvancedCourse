package com.go.android.course.myaidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by go on 2018/1/4.
 */

public class Game implements Parcelable {

    private String name;

    private int userCount;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
