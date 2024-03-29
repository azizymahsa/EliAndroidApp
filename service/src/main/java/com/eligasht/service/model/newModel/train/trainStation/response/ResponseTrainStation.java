
package com.eligasht.service.model.newModel.train.trainStation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseTrainStation {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LoadPropertiesFromId")
    @Expose
    private Object loadPropertiesFromId;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("Icon")
    @Expose
    private String icon;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("TextFa")
    @Expose
    private String textFa;
    @SerializedName("Value")
    @Expose
    private String value;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
