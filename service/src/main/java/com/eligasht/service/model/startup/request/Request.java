
package com.eligasht.service.model.startup.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("AppVersion")
    @Expose
    private String appVersion;
    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("DeviceName")
    @Expose
    private String deviceName;
    @SerializedName("DeviceOSType")
    @Expose
    private String deviceOSType;
    @SerializedName("DeviceProduct")
    @Expose
    private String deviceProduct;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    @SerializedName("IMSI")
    @Expose
    private String iMSI;
    @SerializedName("OperatorName")
    @Expose
    private String operatorName;
    @SerializedName("SDKVersion")
    @Expose
    private String sDKVersion;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceOSType() {
        return deviceOSType;
    }

    public void setDeviceOSType(String deviceOSType) {
        this.deviceOSType = deviceOSType;
    }

    public String getDeviceProduct() {
        return deviceProduct;
    }

    public void setDeviceProduct(String deviceProduct) {
        this.deviceProduct = deviceProduct;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getSDKVersion() {
        return sDKVersion;
    }

    public void setSDKVersion(String sDKVersion) {
        this.sDKVersion = sDKVersion;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
