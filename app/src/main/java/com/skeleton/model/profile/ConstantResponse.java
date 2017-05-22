package com.skeleton.model.profile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mark63 on 17/5/17.
 */

public class ConstantResponse {

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    /**
     * @return get status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode set status code
     */
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return get data
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message set message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data set data
     */
    public void setData(final Data data) {
        this.data = data;
    }
}
