
package com.example.server.imageuploaddemo.POJO.UpdateProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfile {

    private Integer sbStatus;
    private String sbMessages;
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
