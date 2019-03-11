
package com.eligasht.service.model.newModel.startup.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branch {

    @SerializedName("UserCulture")
    @Expose
    private Object userCulture;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("AdjustEnabled")
    @Expose
    private Boolean adjustEnabled;
    @SerializedName("AdjustKey")
    @Expose
    private Object adjustKey;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("DefaultCulture")
    @Expose
    private String defaultCulture;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("PaymentMethod")
    @Expose
    private Object paymentMethod;
    @SerializedName("ConditionUrl")
    @Expose
    private Object conditionUrl;
    @SerializedName("SearchNotes")
    @Expose
    private Object searchNotes;
    @SerializedName("CommonUrl")
    @Expose
    private List<CommonUrl> commonUrl = null;
    @SerializedName("ContactUs")
    @Expose
    private ContactUs contactUs;
    @SerializedName("ActiveOperations")
    @Expose
    private List<Integer> activeOperations = null;
    @SerializedName("IsDefault")
    @Expose
    private Boolean isDefault;

    public Object getUserCulture() {
        return userCulture;
    }

    public void setUserCulture(Object userCulture) {
        this.userCulture = userCulture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdjustEnabled() {
        return adjustEnabled;
    }

    public void setAdjustEnabled(Boolean adjustEnabled) {
        this.adjustEnabled = adjustEnabled;
    }

    public Object getAdjustKey() {
        return adjustKey;
    }

    public void setAdjustKey(Object adjustKey) {
        this.adjustKey = adjustKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDefaultCulture() {
        return defaultCulture;
    }

    public void setDefaultCulture(String defaultCulture) {
        this.defaultCulture = defaultCulture;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Object paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Object getConditionUrl() {
        return conditionUrl;
    }

    public void setConditionUrl(Object conditionUrl) {
        this.conditionUrl = conditionUrl;
    }

    public Object getSearchNotes() {
        return searchNotes;
    }

    public void setSearchNotes(Object searchNotes) {
        this.searchNotes = searchNotes;
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

    public List<Integer> getActiveOperations() {
        return activeOperations;
    }

    public void setActiveOperations(List<Integer> activeOperations) {
        this.activeOperations = activeOperations;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}
