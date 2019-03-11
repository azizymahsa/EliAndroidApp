
package com.eligasht.service.model.newModel.startup.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestStartup {

    @SerializedName("SdkVersion")
    @Expose
    private String sdkVersion;
    @SerializedName("DeviceModelName")
    @Expose
    private String deviceModelName;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("AppVersion")
    @Expose
    private String appVersion;
    @SerializedName("DeviceGuid")
    @Expose
    private String deviceGuid;
    @SerializedName("PubliceKey")
    @Expose
    private String publiceKey;
    @SerializedName("PrivateKey")
    @Expose
    private String privateKey;
    @SerializedName("Culture")
    @Expose
    private String culture;

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceGuid() {
        return deviceGuid;
    }

    public void setDeviceGuid(String deviceGuid) {
        this.deviceGuid = deviceGuid;
    }

    public String getPubliceKey() {
        return publiceKey;
    }

    public void setPubliceKey(String publiceKey) {
        this.publiceKey = publiceKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

}
