package com.eligasht.service.model.newModel.insurance.response.InsuranceCountry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseInsuranceCountry {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LoadPropertiesFromId")
    @Expose
    private Object loadPropertiesFromId;
    @SerializedName("Category")
    @Expose
    private Object category;
    @SerializedName("Icon")
    @Expose
    private Object icon;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("TextFa")
    @Expose
    private String textFa;
    @SerializedName("EValue")
    @Expose
    private String eValue;
    @SerializedName("PItem")
    @Expose
    private Object pItem;
    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("AdditionalCode")
    @Expose
    private Object additionalCode;
    @SerializedName("ParentCode")
    @Expose
    private Object parentCode;
    @SerializedName("IsPersian")
    @Expose
    private Boolean isPersian;
    @SerializedName("IsPopular")
    @Expose
    private Boolean isPopular;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public Object getLoadPropertiesFromId() {
        return loadPropertiesFromId;
    }

    public void setLoadPropertiesFromId(Object loadPropertiesFromId) {
        this.loadPropertiesFromId = loadPropertiesFromId;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextFa() {
        return textFa;
    }

    public void setTextFa(String textFa) {
        this.textFa = textFa;
    }

    public String getEValue() {
        return eValue;
    }

    public void setEValue(String eValue) {
        this.eValue = eValue;
    }

    public Object getPItem() {
        return pItem;
    }

    public void setPItem(Object pItem) {
        this.pItem = pItem;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Object getAdditionalCode() {
        return additionalCode;
    }

    public void setAdditionalCode(Object additionalCode) {
        this.additionalCode = additionalCode;
    }

    public Object getParentCode() {
        return parentCode;
    }

    public void setParentCode(Object parentCode) {
        this.parentCode = parentCode;
    }

    public Boolean getIsPersian() {
        return isPersian;
    }

    public void setIsPersian(Boolean isPersian) {
        this.isPersian = isPersian;
    }

    public Boolean getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(Boolean isPopular) {
        this.isPopular = isPopular;
    }

}
