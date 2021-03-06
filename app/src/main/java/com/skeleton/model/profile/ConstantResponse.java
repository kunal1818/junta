package com.skeleton.model.profile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mark63 on 23/5/17.
 */

public class ConstantResponse {

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    /**
     * kfk
     *
     * @return frr
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * ferr
     *
     * @return frrrgr
     */
    public String getMessage() {
        return message;
    }

    /**
     * fbfbfbbr
     *
     * @return egrgrgr
     */
    public Data getData() {
        return data;
    }
}
