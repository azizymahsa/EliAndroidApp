
package com.eligasht.service.model.hotelflight.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flights {

    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("AccountId1")
    @Expose
    private String accountId1;
    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("ArrRout")
    @Expose
    private String arrRout;
    @SerializedName("ArrivalRout")
    @Expose
    private String arrivalRout;
    @SerializedName("DepRout")
    @Expose
    private String depRout;
    @SerializedName("DepartureRout")
    @Expose
    private String departureRout;
    @SerializedName("EAccountId")
    @Expose
    private String eAccountId;
    @SerializedName("EFlightId")
    @Expose
    private String eFlightId;
    @SerializedName("FlightGUID")
    @Expose
    private String flightGUID;
    @SerializedName("FlightID")
    @Expose
    private String flightID;
    @SerializedName("FlightId1")
    @Expose
    private String flightId1;
    @SerializedName("FlightTypeID")
    @Expose
    private Integer flightTypeID;
    @SerializedName("FltList")
    @Expose
    private List<FltList> fltList = null;
    @SerializedName("SourceAmount")
    @Expose
    private Integer sourceAmount;
    @SerializedName("Stops")
    @Expose
    private Integer stops;
    @SerializedName("TotalCost")
    @Expose
    private Object totalCost;
    @SerializedName("TotalFare")
    @Expose
    private Object totalFare;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountId1() {
        return accountId1;
    }

    public void setAccountId1(String accountId1) {
        this.accountId1 = accountId1;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getArrRout() {
        return arrRout;
    }

    public void setArrRout(String arrRout) {
        this.arrRout = arrRout;
    }

    public String getArrivalRout() {
        return arrivalRout;
    }

    public void setArrivalRout(String arrivalRout) {
        this.arrivalRout = arrivalRout;
    }

    public String getDepRout() {
        return depRout;
    }

    public void setDepRout(String depRout) {
        this.depRout = depRout;
    }

    public String getDepartureRout() {
        return departureRout;
    }

    public void setDepartureRout(String departureRout) {
        this.departureRout = departureRout;
    }

    public String getEAccountId() {
        return eAccountId;
    }

    public void setEAccountId(String eAccountId) {
        this.eAccountId = eAccountId;
    }

    public String getEFlightId() {
        return eFlightId;
    }

    public void setEFlightId(String eFlightId) {
        this.eFlightId = eFlightId;
    }

    public String getFlightGUID() {
        return flightGUID;
    }

    public void setFlightGUID(String flightGUID) {
        this.flightGUID = flightGUID;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getFlightId1() {
        return flightId1;
    }

    public void setFlightId1(String flightId1) {
        this.flightId1 = flightId1;
    }

    public Integer getFlightTypeID() {
        return flightTypeID;
    }

    public void setFlightTypeID(Integer flightTypeID) {
        this.flightTypeID = flightTypeID;
    }

    public List<FltList> getFltList() {
        return fltList;
    }

    public void setFltList(List<FltList> fltList) {
        this.fltList = fltList;
    }

    public Integer getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Integer sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Integer getStops() {
        return stops;
    }

    public void setStops(Integer stops) {
        this.stops = stops;
    }

    public Object getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Object totalCost) {
        this.totalCost = totalCost;
    }

    public Object getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Object totalFare) {
        this.totalFare = totalFare;
    }

}
