
package com.eligasht.service.model.newModel.startup.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branch {

    @SerializedName("ActiveOperations")
    @Expose
    private List<Integer> activeOperations = null;
    @SerializedName("CommonUrl")
    @Expose
    private List<CommonUrl> commonUrl = null;
    @SerializedName("ContactUs")
    @Expose
    private ContactUs contactUs;
    @SerializedName("DefaultCulture")
    @Expose
    private String defaultCulture;
    @SerializedName("IsDefault")
    @Expose
    private Boolean isDefault;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("AvailabelLanguageList")
    @Expose
    private List<String> availabelLanguageList = null;
    @SerializedName("AdjustTrackerId")
    @Expose
    private Object adjustTrackerId;
    @SerializedName("AdtraceTrackerID")
    @Expose
    private Object adtraceTrackerID;
    @SerializedName("OneSignalTrackerID")
    @Expose
    private Object oneSignalTrackerID;

    public List<Integer> getActiveOperations() {
        return activeOperations;
    }

    public void setActiveOperations(List<Integer> activeOperations) {
        this.activeOperations = activeOperations;
    }

    public List<CommonUrl> getCommonUrl() {
        return commonUrl;
    }

    public void setCommonUrl(List<CommonUrl> commonUrl) {
        this.commonUrl = commonUrl;
    }

    public ContactUs getContactUs() {
        return contactUs;
    }

    public void setContactUs(ContactUs contactUs) {
        this.contactUs = contactUs;
    }

    public String getDefaultCulture() {
        return defaultCulture;
    }

    public void setDefaultCulture(String defaultCulture) {
        this.defaultCulture = defaultCulture;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getAvailabelLanguageList() {
        return availabelLanguageList;
    }

    public void setAvailabelLanguageList(List<String> availabelLanguageList) {
        this.availabelLanguageList = availabelLanguageList;
    }

    public Object getAdjustTrackerId() {
        return adjustTrackerId;
    }

    public void setAdjustTrackerId(Object adjustTrackerId) {
        this.adjustTrackerId = adjustTrackerId;
    }

    public Object getAdtraceTrackerID() {
        return adtraceTrackerID;
    }

    public void setAdtraceTrackerID(Object adtraceTrackerID) {
        this.adtraceTrackerID = adtraceTrackerID;
    }

    public Object getOneSignalTrackerID() {
        return oneSignalTrackerID;
    }

    public void setOneSignalTrackerID(Object oneSignalTrackerID) {
        this.oneSignalTrackerID = oneSignalTrackerID;
    }

}
