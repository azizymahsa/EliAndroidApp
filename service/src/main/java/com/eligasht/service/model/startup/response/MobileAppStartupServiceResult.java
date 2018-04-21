
package com.eligasht.service.model.startup.response;

import com.eligasht.service.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class MobileAppStartupServiceResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("AdjustEnabled")
    @Expose
    private Boolean adjustEnabled;
    @SerializedName("AppVersion")
    @Expose
    private Object appVersion;
    @SerializedName("Brand")
    @Expose
    private Object brand;
    @SerializedName("Culture")
    @Expose
    private Object culture;
    @SerializedName("CultureDefault")
    @Expose
    private String cultureDefault;
    @SerializedName("DeviceName")
    @Expose
    private Object deviceName;
    @SerializedName("DeviceOSType")
    @Expose
    private Integer deviceOSType;
    @SerializedName("DeviceProduct")
    @Expose
    private Object deviceProduct;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    @SerializedName("IMSI")
    @Expose
    private String iMSI;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("OpratorName")
    @Expose
    private Object opratorName;
    @SerializedName("SDKVersion")
    @Expose
    private Integer sDKVersion;
    @SerializedName("ServerID")
    @Expose
    private Integer serverID;
    @SerializedName("UserEntranceResponse")
    @Expose
    private UserEntranceResponse userEntranceResponse;
    @SerializedName("identity")
    @Expose
    private Object identity;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public Boolean getAdjustEnabled() {
        return adjustEnabled;
    }

    public void setAdjustEnabled(Boolean adjustEnabled) {
        this.adjustEnabled = adjustEnabled;
    }

    public Object getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Object appVersion) {
        this.appVersion = appVersion;
    }

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object brand) {
        this.brand = brand;
    }

    public Object getCulture() {
        return culture;
    }

    public void setCulture(Object culture) {
        this.culture = culture;
    }

    public String getCultureDefault() {
        return cultureDefault;
    }

    public void setCultureDefault(String cultureDefault) {
        this.cultureDefault = cultureDefault;
    }

    public Object getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(Object deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceOSType() {
        return deviceOSType;
    }

    public void setDeviceOSType(Integer deviceOSType) {
        this.deviceOSType = deviceOSType;
    }

    public Object getDeviceProduct() {
        return deviceProduct;
    }

    public void setDeviceProduct(Object deviceProduct) {
        this.deviceProduct = deviceProduct;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getIMEI() {
        return iMEI;
    }

    public void setIMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public String getIMSI() {
        return iMSI;
    }

    public void setIMSI(String iMSI) {
        this.iMSI = iMSI;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getOpratorName() {
        return opratorName;
    }

    public void setOpratorName(Object opratorName) {
        this.opratorName = opratorName;
    }

    public Integer getSDKVersion() {
        return sDKVersion;
    }

    public void setSDKVersion(Integer sDKVersion) {
        this.sDKVersion = sDKVersion;
    }

    public Integer getServerID() {
        return serverID;
    }

    public void setServerID(Integer serverID) {
        this.serverID = serverID;
    }

    public UserEntranceResponse getUserEntranceResponse() {
        return userEntranceResponse;
    }

    public void setUserEntranceResponse(UserEntranceResponse userEntranceResponse) {
        this.userEntranceResponse = userEntranceResponse;
    }

    public Object getIdentity() {
        return identity;
    }

    public void setIdentity(Object identity) {
        this.identity = identity;
    }

}
