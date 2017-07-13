
package com.example.server.imageuploaddemo.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("sb_status")
    @Expose
    private Integer sbStatus;
    @SerializedName("sb_messages")
    @Expose
    private String sbMessages;
    @SerializedName("sb_response")
    @Expose
    private SbResponse sbResponse;

    public Integer getSbStatus() {
        return sbStatus;
    }

    public void setSbStatus(Integer sbStatus) {
        this.sbStatus = sbStatus;
    }

    public String getSbMessages() {
        return sbMessages;
    }

    public void setSbMessages(String sbMessages) {
        this.sbMessages = sbMessages;
    }

    public SbResponse getSbResponse() {
        return sbResponse;
    }

    public void setSbResponse(SbResponse sbResponse) {
        this.sbResponse = sbResponse;
    }

}
