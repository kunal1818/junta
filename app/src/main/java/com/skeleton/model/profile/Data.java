package com.skeleton.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * jijio
 */
public class Data {
    @SerializedName("Orientation")
    private List<String> mOrientation;
    @SerializedName("RelationshipHistory")
    private List<String> mRelationshipHistory;
    @SerializedName("Ethnicity")
    private List<String> mEthnicity;
    @SerializedName("Religion")
    private List<String> mReligion;
    @SerializedName("BodyType")
    private List<String> mBodyType;
    @SerializedName("Smoking")
    private List<String> mSmoking;
    @SerializedName("Drinking")
    private List<String> mDrinking;
    @SerializedName("Height")
    private List<String> mHeight;

    /**
     * @return get orientation from here
     */
    public List<String> getOrientation() {
        return mOrientation;
    }

    /**
     * vff
     *
     * @return get realatioship from here
     */
    public List<String> getRelationshipHistory() {
        return mRelationshipHistory;
    }

    /**
     * vff
     *
     * @return get enthicity from here
     */
    public List<String> getEthnicity() {
        return mEthnicity;
    }

    /**
     * vff
     *
     * @return get religion from here
     */

    public List<String> getReligion() {
        return mReligion;
    }

    /**
     * vff
     *
     * @return get Bodytype from here
     */

    public List<String> getBodyType() {
        return mBodyType;
    }

    /**
     * vff
     *
     * @return get smoking  from here
     */

    public List<String> getSmoking() {
        return mSmoking;
    }

    /**
     * vff
     *
     * @return get drinking  from here
     */

    public List<String> getDrinking() {
        return mDrinking;
    }

    /**
     * vff
     *
     * @return get Height from here
     */

    public List<String> getHeight() {
        return mHeight;
    }
}
