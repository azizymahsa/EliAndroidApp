
package com.eligasht.service.model.flight.response.searchFlight;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("AdlBaseFare")
    @Expose
    private AdlBaseFare adlBaseFare;
    @SerializedName("AdlCost")
    @Expose
    private AdlCost adlCost;
    @SerializedName("AdlTotalFare")
    @Expose
    private AdlTotalFare adlTotalFare;
    @SerializedName("Adults")
    @Expose
    private Integer adults;
    @SerializedName("BaseFare")
    @Expose
    private BaseFare baseFare;
    @SerializedName("ChdBaseFare")
    @Expose
    private ChdBaseFare chdBaseFare;
    @SerializedName("ChdCost")
    @Expose
    private ChdCost chdCost;
    @SerializedName("ChdTotalFare")
    @Expose
    private ChdTotalFare chdTotalFare;
    @SerializedName("Childs")
    @Expose
    private Integer childs;
    @SerializedName("FareTypeID")
    @Expose
    private Integer fareTypeID;
    @SerializedName("FlightGUID")
    @Expose
    private String flightGUID;
    @SerializedName("FlightID")
    @Expose
    private String flightID;
    @SerializedName("FlightTypeID")
    @Expose
    private Integer flightTypeID;
    @SerializedName("InfBaseFare")
    @Expose
    private InfBaseFare infBaseFare;
    @SerializedName("InfCost")
    @Expose
    private InfCost infCost;
    @SerializedName("InfTotalFare")
    @Expose
    private InfTotalFare infTotalFare;
    @SerializedName("Infants")
    @Expose
    private Integer infants;
    @SerializedName("IsCharter")
    @Expose
    private Boolean isCharter;
    @SerializedName("IsIntegration")
    @Expose
    private Boolean isIntegration;
    @SerializedName("IsOpenjaw")
    @Expose
    private Boolean isOpenjaw;
    @SerializedName("OneWay")
    @Expose
    private Boolean oneWay;
    @SerializedName("Passengers")
    @Expose
    private String passengers;
    @SerializedName("RemainSeats")
    @Expose
    private Integer remainSeats;
    @SerializedName("SegmentList")
    @Expose
    private List<SegmentList> segmentList = null;
    @SerializedName("Taxes")
    @Expose
    private Taxes taxes;
    @SerializedName("TotalFare")
    @Expose
    private TotalFare totalFare;
    @SerializedName("TotalFareCost")
    @Expose
    private TotalFareCost totalFareCost;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public AdlBaseFare getAdlBaseFare() {
        return adlBaseFare;
    }

    public void setAdlBaseFare(AdlBaseFare adlBaseFare) {
        this.adlBaseFare = adlBaseFare;
    }

    public AdlCost getAdlCost() {
        return adlCost;
    }

    public void setAdlCost(AdlCost adlCost) {
        this.adlCost = adlCost;
    }

    public AdlTotalFare getAdlTotalFare() {
        return adlTotalFare;
    }

    public void setAdlTotalFare(AdlTotalFare adlTotalFare) {
        this.adlTotalFare = adlTotalFare;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public BaseFare getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BaseFare baseFare) {
        this.baseFare = baseFare;
    }

    public ChdBaseFare getChdBaseFare() {
        return chdBaseFare;
    }

    public void setChdBaseFare(ChdBaseFare chdBaseFare) {
        this.chdBaseFare = chdBaseFare;
    }

    public ChdCost getChdCost() {
        return chdCost;
    }

    public void setChdCost(ChdCost chdCost) {
        this.chdCost = chdCost;
    }

    public ChdTotalFare getChdTotalFare() {
        return chdTotalFare;
    }

    public void setChdTotalFare(ChdTotalFare chdTotalFare) {
        this.chdTotalFare = chdTotalFare;
    }

    public Integer getChilds() {
        return childs;
    }

    public void setChilds(Integer childs) {
        this.childs = childs;
    }

    public Integer getFareTypeID() {
        return fareTypeID;
    }

    public void setFareTypeID(Integer fareTypeID) {
        this.fareTypeID = fareTypeID;
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

    public Integer getFlightTypeID() {
        return flightTypeID;
    }

    public void setFlightTypeID(Integer flightTypeID) {
        this.flightTypeID = flightTypeID;
    }

    public InfBaseFare getInfBaseFare() {
        return infBaseFare;
    }

    public void setInfBaseFare(InfBaseFare infBaseFare) {
        this.infBaseFare = infBaseFare;
    }

    public InfCost getInfCost() {
        return infCost;
    }

    public void setInfCost(InfCost infCost) {
        this.infCost = infCost;
    }

    public InfTotalFare getInfTotalFare() {
        return infTotalFare;
    }

    public void setInfTotalFare(InfTotalFare infTotalFare) {
        this.infTotalFare = infTotalFare;
    }

    public Integer getInfants() {
        return infants;
    }

    public void setInfants(Integer infants) {
        this.infants = infants;
    }

    public Boolean getIsCharter() {
        return isCharter;
    }

    public void setIsCharter(Boolean isCharter) {
        this.isCharter = isCharter;
    }

    public Boolean getIsIntegration() {
        return isIntegration;
    }

    public void setIsIntegration(Boolean isIntegration) {
        this.isIntegration = isIntegration;
    }

    public Boolean getIsOpenjaw() {
        return isOpenjaw;
    }

    public void setIsOpenjaw(Boolean isOpenjaw) {
        this.isOpenjaw = isOpenjaw;
    }

    public Boolean getOneWay() {
        return oneWay;
    }

    public void setOneWay(Boolean oneWay) {
        this.oneWay = oneWay;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public Integer getRemainSeats() {
        return remainSeats;
    }

    public void setRemainSeats(Integer remainSeats) {
        this.remainSeats = remainSeats;
    }

    public List<SegmentList> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<SegmentList> segmentList) {
        this.segmentList = segmentList;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public TotalFare getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(TotalFare totalFare) {
        this.totalFare = totalFare;
    }

    public TotalFareCost getTotalFareCost() {
        return totalFareCost;
    }

    public void setTotalFareCost(TotalFareCost totalFareCost) {
        this.totalFareCost = totalFareCost;
    }

}