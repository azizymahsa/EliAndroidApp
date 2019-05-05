
package com.eligasht.service.model.newModel.hotelFlight.loadFlight.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HFlight {

    @SerializedName("FlightID")
    @Expose
    private String flightID;
    @SerializedName("FlightTypeID")
    @Expose
    private Integer flightTypeID;
    @SerializedName("AccountID")
    @Expose
    private Object accountID;
    @SerializedName("FlightGUID")
    @Expose
    private String flightGUID;
    @SerializedName("DepartureRout")
    @Expose
    private Object departureRout;
    @SerializedName("ArrivalRout")
    @Expose
    private Object arrivalRout;
    @SerializedName("Stops")
    @Expose
    private Integer stops;
    @SerializedName("FltList")
    @Expose
    private List<FltList> fltList = null;
    @SerializedName("TotalFare")
    @Expose
    private Object totalFare;
    @SerializedName("TotalCost")
    @Expose
    private Object totalCost;
    @SerializedName("FlightId")
    @Expose
    private String flightId;
    @SerializedName("EFlightId")
    @Expose
    private String eFlightId;
    @SerializedName("AccountId")
    @Expose
    private String accountId;
    @SerializedName("EAccountId")
    @Expose
    private String eAccountId;
    @SerializedName("SourceAmount")
    @Expose
    private Integer sourceAmount;
    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("ArrRout")
    @Expose
    private String arrRout;
    @SerializedName("DepRout")
    @Expose
    private String depRout;
    @SerializedName("OfferId")
    @Expose
    private String offerId;

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public Integer getFlightTypeID() {
        return flightTypeID;
    }

    public void setFlightTypeID(Integer flightTypeID) {
        this.flightTypeID = flightTypeID;
    }

    public Object getAccountID() {
        return accountID;
    }

    public void setAccountID(Object accountID) {
        this.accountID = accountID;
    }

    public String getFlightGUID() {
        return flightGUID;
    }

    public void setFlightGUID(String flightGUID) {
        this.flightGUID = flightGUID;
    }

    public Object getDepartureRout() {
        return departureRout;
    }

    public void setDepartureRout(Object departureRout) {
        this.departureRout = departureRout;
    }

    public Object getArrivalRout() {
        return arrivalRout;
    }

    public void setArrivalRout(Object arrivalRout) {
        this.arrivalRout = arrivalRout;
    }

    public Integer getStops() {
        return stops;
    }

    public void setStops(Integer stops) {
        this.stops = stops;
    }

    public List<FltList> getFltList() {
        return fltList;
    }

    public void setFltList(List<FltList> fltList) {
        this.fltList = fltList;
    }

    public Object getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Object totalFare) {
        this.totalFare = totalFare;
    }

    public Object getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Object totalCost) {
        this.totalCost = totalCost;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getEFlightId() {
        return eFlightId;
    }

    public void setEFlightId(String eFlightId) {
        this.eFlightId = eFlightId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEAccountId() {
        return eAccountId;
    }

    public void setEAccountId(String eAccountId) {
        this.eAccountId = eAccountId;
    }

    public Integer getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Integer sourceAmount) {
        this.sourceAmount = sourceAmount;
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

    public String getDepRout() {
        return depRout;
    }

    public void setDepRout(String depRout) {
        this.depRout = depRout;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

}
