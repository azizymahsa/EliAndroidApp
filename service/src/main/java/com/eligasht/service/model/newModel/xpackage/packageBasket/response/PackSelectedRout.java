
package com.eligasht.service.model.newModel.xpackage.packageBasket.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackSelectedRout {

    @SerializedName("RoutID")
    @Expose
    private Integer routID;
    @SerializedName("RoutTitle")
    @Expose
    private Object routTitle;
    @SerializedName("RoutNameFa")
    @Expose
    private String routNameFa;
    @SerializedName("RoutNameEn")
    @Expose
    private String routNameEn;
    @SerializedName("MinPrice")
    @Expose
    private Object minPrice;
    @SerializedName("AvailableDates")
    @Expose
    private Object availableDates;

    public Integer getRoutID() {
        return routID;
    }

    public void setRoutID(Integer routID) {
        this.routID = routID;
    }

    public Object getRoutTitle() {
        return routTitle;
    }

    public void setRoutTitle(Object routTitle) {
        this.routTitle = routTitle;
    }

    public String getRoutNameFa() {
        return routNameFa;
    }

    public void setRoutNameFa(String routNameFa) {
        this.routNameFa = routNameFa;
    }

    public String getRoutNameEn() {
        return routNameEn;
    }

    public void setRoutNameEn(String routNameEn) {
        this.routNameEn = routNameEn;
    }

    public Object getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Object minPrice) {
        this.minPrice = minPrice;
    }

    public Object getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(Object availableDates) {
        this.availableDates = availableDates;
    }

}
