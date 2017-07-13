
package com.example.server.imageuploaddemo.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData_ {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("is_guest")
    @Expose
    private String isGuest;
    @SerializedName("v_first_name")
    @Expose
    private String vFirstName;
    @SerializedName("v_last_name")
    @Expose
    private String vLastName;
    @SerializedName("v_email")
    @Expose
    private String vEmail;
    @SerializedName("v_password")
    @Expose
    private String vPassword;
    @SerializedName("v_gender")
    @Expose
    private String vGender;
    @SerializedName("d_dob")
    @Expose
    private String dDob;
    @SerializedName("v_phone")
    @Expose
    private String vPhone;
    @SerializedName("v_image")
    @Expose
    private String vImage;
    @SerializedName("i_auth_code")
    @Expose
    private String iAuthCode;
    @SerializedName("e_status")
    @Expose
    private String eStatus;
    @SerializedName("d_added")
    @Expose
    private String dAdded;
    @SerializedName("d_modified")
    @Expose
    private String dModified;
    @SerializedName("v_link")
    @Expose
    private String vLink;
    @SerializedName("v_device_token")
    @Expose
    private String vDeviceToken;
    @SerializedName("d_last_log")
    @Expose
    private String dLastLog;
    @SerializedName("d_last_log_ip")
    @Expose
    private String dLastLogIp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsGuest() {
        return isGuest;
    }

    public void setIsGuest(String isGuest) {
        this.isGuest = isGuest;
    }

    public String getVFirstName() {
        return vFirstName;
    }

    public void setVFirstName(String vFirstName) {
        this.vFirstName = vFirstName;
    }

    public String getVLastName() {
        return vLastName;
    }

    public void setVLastName(String vLastName) {
        this.vLastName = vLastName;
    }

    public String getVEmail() {
        return vEmail;
    }

    public void setVEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getVPassword() {
        return vPassword;
    }

    public void setVPassword(String vPassword) {
        this.vPassword = vPassword;
    }

    public String getVGender() {
        return vGender;
    }

    public void setVGender(String vGender) {
        this.vGender = vGender;
    }

    public String getDDob() {
        return dDob;
    }

    public void setDDob(String dDob) {
        this.dDob = dDob;
    }

    public String getVPhone() {
        return vPhone;
    }

    public void setVPhone(String vPhone) {
        this.vPhone = vPhone;
    }

    public String getVImage() {
        return vImage;
    }

    public void setVImage(String vImage) {
        this.vImage = vImage;
    }

    public String getIAuthCode() {
        return iAuthCode;
    }

    public void setIAuthCode(String iAuthCode) {
        this.iAuthCode = iAuthCode;
    }

    public String getEStatus() {
        return eStatus;
    }

    public void setEStatus(String eStatus) {
        this.eStatus = eStatus;
    }

    public String getDAdded() {
        return dAdded;
    }

    public void setDAdded(String dAdded) {
        this.dAdded = dAdded;
    }

    public String getDModified() {
        return dModified;
    }

    public void setDModified(String dModified) {
        this.dModified = dModified;
    }

    public String getVLink() {
        return vLink;
    }

    public void setVLink(String vLink) {
        this.vLink = vLink;
    }

    public String getVDeviceToken() {
        return vDeviceToken;
    }

    public void setVDeviceToken(String vDeviceToken) {
        this.vDeviceToken = vDeviceToken;
    }

    public String getDLastLog() {
        return dLastLog;
    }

    public void setDLastLog(String dLastLog) {
        this.dLastLog = dLastLog;
    }

    public String getDLastLogIp() {
        return dLastLogIp;
    }

    public void setDLastLogIp(String dLastLogIp) {
        this.dLastLogIp = dLastLogIp;
    }

}
