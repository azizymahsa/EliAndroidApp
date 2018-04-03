
package com.eligasht.service.model.flight.request.searchFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("DepartureAirportcode")
    @Expose
    private String departureAirportcode;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("ArrivalAirportcode")
    @Expose
    private String arrivalAirportcode;
    @SerializedName("DepartureDate")
    @Expose
    private String departureDate;
    @SerializedName("ArrivalDate")
    @Expose
    private String arrivalDate;
    @SerializedName("OneWay")
    @Expose
    private String oneWay;
    @SerializedName("CabinClassCode")
    @Expose
    private String cabinClassCode;
    @SerializedName("AdlCount")
    @Expose
    private Integer adlCount;
    @SerializedName("ChdCount")
    @Expose
    private Integer chdCount;
    @SerializedName("InfCount")
    @Expose
    private Integer infCount;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getDepartureAirportcode() {
        return departureAirportcode;
    }

    public void setDepartureAirportcode(String departureAirportcode) {
        this.departureAirportcode = departureAirportcode;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getArrivalAirportcode() {
        return arrivalAirportcode;
    }

    public void setArrivalAirportcode(String arrivalAirportcode) {
        this.arrivalAirportcode = arrivalAirportcode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getOneWay() {
        return oneWay;
    }

    public void setOneWay(String oneWay) {
        this.oneWay = oneWay;
    }

    public String getCabinClassCode() {
        return cabinClassCode;
    }

    public void setCabinClassCode(String cabinClassCode) {
        this.cabinClassCode = cabinClassCode;
    }

    public Integer getAdlCount() {
        return adlCount;
    }

    public void setAdlCount(Integer adlCount) {
        this.adlCount = adlCount;
    }

    public Integer getChdCount() {
        return chdCount;
    }

    public void setChdCount(Integer chdCount) {
        this.chdCount = chdCount;
    }

    public Integer getInfCount() {
        return infCount;
    }

    public void setInfCount(Integer infCount) {
        this.infCount = infCount;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
