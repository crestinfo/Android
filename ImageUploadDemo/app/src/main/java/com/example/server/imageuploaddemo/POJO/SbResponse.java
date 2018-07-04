
package com.example.server.imageuploaddemo.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SbResponse {

    @SerializedName("user_data")
    @Expose
    private UserData_ userData;
    @SerializedName("comment_data")
    @Expose
    private Object commentData;

    public UserData_ getUserData() {
        return userData;
    }

    public void setUserData(UserData_ userData) {
        this.userData = userData;
    }

    public Object getCommentData() {
        return commentData;
    }

    public void setCommentData(Object commentData) {
        this.commentData = commentData;
    }

}
