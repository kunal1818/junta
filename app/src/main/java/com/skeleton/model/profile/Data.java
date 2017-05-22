package com.skeleton.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * data class
 */
public class Data {
    @SerializedName("orientation")
    private List<String> orientation;
    @SerializedName("relationshipHistory")
    private List<String> relationshipHistory;
    @SerializedName("ethnicity")
    private List<String> ethnicity;
    @SerializedName("religion")
    private List<String> religion;
    @SerializedName("bodyType")
    private List<String> bodyType;
    @SerializedName("smoking")
    private List<String> smoking;
    @SerializedName("drinking")
    private List<String> drinking;
    @SerializedName("height")
    private List<String> height;

    /**
     * @return orientation of user
     */
    public List<String> getOrientation() {
        return orientation;
    }

    /**
     * @param orientation orientation of user
     */
    public void setOrientation(final List<String> orientation) {
        this.orientation = orientation;
    }

    /**
     * @return relationship history of user
     */
    public List<String> getRelationshipHistory() {
        return relationshipHistory;
    }

    /**
     * @param relationshipHistory relation history of user
     */
    public void setRelationshipHistory(final List<String> relationshipHistory) {
        this.relationshipHistory = relationshipHistory;
    }

    /**
     * @return ethnicity of user
     */
    public List<String> getEthnicity() {
        return ethnicity;
    }

    /**
     * @param ethnicity of user
     */
    public void setEthnicity(final List<String> ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * @return religion of user
     */
    public List<String> getReligion() {
        return religion;
    }

    /**
     * @param religion of user
     */
    public void setReligion(final List<String> religion) {
        this.religion = religion;
    }

    /**
     * @return body Type
     */
    public List<String> getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType of user
     */
    public void setBodyType(final List<String> bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * @return smoking
     */
    public List<String> getSmoking() {
        return smoking;
    }

    /**
     * @param smoking habits of user
     */
    public void setSmoking(final List<String> smoking) {
        this.smoking = smoking;
    }

    /**
     * @return drinking
     */
    public List<String> getDrinking() {
        return drinking;
    }

    /**
     * @param drinking of user
     */
    public void setDrinking(final List<String> drinking) {
        this.drinking = drinking;
    }

    /**
     * @return height of user
     */
    public List<String> getHeight() {
        return height;
    }

    /**
     * @param height of user
     */
    public void setHeight(final List<String> height) {
        this.height = height;
    }
}
