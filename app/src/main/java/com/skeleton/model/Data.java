package com.skeleton.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mark63 on 11/5/17.
 */

public class Data implements Parcelable {
    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(final Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(final int size) {
            return new Data[size];
        }
    };
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("userDetails")
    private UserDetails userDetails;

    /**
     * @param in input
     */
    protected Data(final Parcel in) {
        accessToken = in.readString();
    }


    /**
     * @return access token of user
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken access token
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return get user details object
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * @param userDetails value of user details class
     */
    public void setUserDetails(final UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(accessToken);
    }
}
