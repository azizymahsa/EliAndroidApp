package com.eligasht.service.model.newModel.airport.response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResponseAirport {

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

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String geteValue() {
        return eValue;
    }

    public void seteValue(String eValue) {
        this.eValue = eValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getpItem() {
        return pItem;
    }

    public void setpItem(String pItem) {
        this.pItem = pItem;
    }

    public Boolean getPersian() {
        return isPersian;
    }

    public void setPersian(Boolean persian) {
        isPersian = persian;
    }

    public Boolean getPopular() {
        return isPopular;
    }

    public void setPopular(Boolean popular) {
        isPopular = popular;
    }

    @SerializedName("TextFa")
    @Expose

    private String textFa;
    @SerializedName("EValue")
    @Expose
    private String eValue;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("PItem")
    @Expose
    private String pItem;
    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("AdditionalCode")
    @Expose
    private Object additionalCode;
    @SerializedName("ParentCode")
    @Expose
    private String parentCode;
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

    public String getEValue() {
        return eValue;
    }

    public void setEValue(String eValue) {
        this.eValue = eValue;
    }

    public String getPItem() {
        return pItem;
    }

    public void setPItem(String pItem) {
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
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
