package com.skeleton.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mark63 on 11/5/17.
 */

public class UserDetails {
    @SerializedName("id")
    private String id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("dob")
    private String dob;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("phoneNo")
    private String phoneNo;
    @SerializedName("email")
    private String email;
    @SerializedName("newNumber")
    private String newNumber;
    @SerializedName("userImages")
    private List<UserImages> userImages;

    /**
     * @return get user id from server
     */
    public String getId() {
        return id;
    }

    /**
     * @param id set user id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return at what time user profile is created
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt set Created At
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return Last time Updated profile
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt set Last time Updated profile
     */
    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return get date of birth of user
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob set data of birth of user
     */
    public void setDob(final String dob) {
        this.dob = dob;
    }

    /**
     * @return return country code of user
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode set country code
     */
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return phone number of user
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo set phone number of user
     */
    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return get email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email set email of user
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return get number of user
     */
    public String getNewNumber() {
        return newNumber;
    }

    /**
     * @param newNumber set number of user
     */
    public void setNewNumber(final String newNumber) {
        this.newNumber = newNumber;
    }

    /**
     * @return list of images of user
     */
    public List<UserImages> getUserImages() {
        return userImages;
    }

    /**
     * @param userImages list of images of users
     */
    public void setUserImages(final List<UserImages> userImages) {
        this.userImages = userImages;
    }
}
