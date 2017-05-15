package com.skeleton.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mark63 on 11/5/17.
 */

public class UserImages {
    @SerializedName("_id")
    private String _id;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("original")
    private String original;

    /**
     * @return get id of the user image
     */
    public String get_id() {
        return _id;
    }

    /**
     * @return get thumbnail of the user image
     */
    public String getThumbnail() {
        return thumbnail;
    }


    /**
     * @return get original image of user
     */
    public String getOriginal() {
        return original;
    }
}
