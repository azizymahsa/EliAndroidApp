
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OfferCode_ {

    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("Code")
    @Expose
    private Object code;
    @SerializedName("CountryOfResidence")
    @Expose
    private Object countryOfResidence;
    @SerializedName("DropOff")
    @Expose
    private DropOff___ dropOff;
    @SerializedName("ItemCode")
    @Expose
    private Object itemCode;
    @SerializedName("MaxNumberOfPassengers")
    @Expose
    private Integer maxNumberOfPassengers;
    @SerializedName("Nationality")
    @Expose
    private Object nationality;
    @SerializedName("PickUp")
    @Expose
    private PickUp__ pickUp;
    @SerializedName("Supplier")
    @Expose
    private String supplier;
    @SerializedName("SupplierSession")
    @Expose
    private String supplierSession;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(Object countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public DropOff___ getDropOff() {
        return dropOff;
    }

    public void setDropOff(DropOff___ dropOff) {
        this.dropOff = dropOff;
    }

    public Object getItemCode() {
        return itemCode;
    }

    public void setItemCode(Object itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public void setMaxNumberOfPassengers(Integer maxNumberOfPassengers) {
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    public Object getNationality() {
        return nationality;
    }

    public void setNationality(Object nationality) {
        this.nationality = nationality;
    }

    public PickUp__ getPickUp() {
        return pickUp;
    }

    public void setPickUp(PickUp__ pickUp) {
        this.pickUp = pickUp;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierSession() {
        return supplierSession;
    }

    public void setSupplierSession(String supplierSession) {
        this.supplierSession = supplierSession;
    }



}
