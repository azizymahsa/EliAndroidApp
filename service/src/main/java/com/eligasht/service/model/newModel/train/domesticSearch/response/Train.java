
package com.eligasht.service.model.newModel.train.domesticSearch.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Train {

    @SerializedName("FareTypeID")
    @Expose
    private Integer fareTypeID;
    @SerializedName("TrainID")
    @Expose
    private String trainID;
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("TrainTypeID")
    @Expose
    private Integer trainTypeID;
    @SerializedName("TrainGUID")
    @Expose
    private String trainGUID;
    @SerializedName("Taxes")
    @Expose
    private Object taxes;
    @SerializedName("BaseFare")
    @Expose
    private Object baseFare;
    @SerializedName("TotalFare")
    @Expose
    private TotalFare totalFare;
    @SerializedName("TotalFareCost")
    @Expose
    private Object totalFareCost;
    @SerializedName("AdlBaseFare")
    @Expose
    private Object adlBaseFare;
    @SerializedName("ChdBaseFare")
    @Expose
    private Object chdBaseFare;
    @SerializedName("InfBaseFare")
    @Expose
    private Object infBaseFare;
    @SerializedName("AdlTotalFare")
    @Expose
    private Object adlTotalFare;
    @SerializedName("ChdTotalFare")
    @Expose
    private Object chdTotalFare;
    @SerializedName("InfTotalFare")
    @Expose
    private Object infTotalFare;
    @SerializedName("AdlCost")
    @Expose
    private Object adlCost;
    @SerializedName("ChdCost")
    @Expose
    private Object chdCost;
    @SerializedName("InfCost")
    @Expose
    private Object infCost;
    @SerializedName("OneWay")
    @Expose
    private Boolean oneWay;
    @SerializedName("IsOpenjaw")
    @Expose
    private Boolean isOpenjaw;
    @SerializedName("IsIntegration")
    @Expose
    private Boolean isIntegration;
    @SerializedName("SegmentList")
    @Expose
    private List<SegmentList> segmentList = null;
    @SerializedName("Passengers")
    @Expose
    private String passengers;
    @SerializedName("NoteBaggage")
    @Expose
    private String noteBaggage;
    @SerializedName("Error")
    @Expose
    private Object error;

    public Integer getFareTypeID() {
        return fareTypeID;
    }

    public void setFareTypeID(Integer fareTypeID) {
        this.fareTypeID = fareTypeID;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getTrainTypeID() {
        return trainTypeID;
    }

    public void setTrainTypeID(Integer trainTypeID) {
        this.trainTypeID = trainTypeID;
    }

    public String getTrainGUID() {
        return trainGUID;
    }

    public void setTrainGUID(String trainGUID) {
        this.trainGUID = trainGUID;
    }

    public Object getTaxes() {
        return taxes;
    }

    public void setTaxes(Object taxes) {
        this.taxes = taxes;
    }

    public Object getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Object baseFare) {
        this.baseFare = baseFare;
    }

    public TotalFare getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(TotalFare totalFare) {
        this.totalFare = totalFare;
    }

    public Object getTotalFareCost() {
        return totalFareCost;
    }

    public void setTotalFareCost(Object totalFareCost) {
        this.totalFareCost = totalFareCost;
    }

    public Object getAdlBaseFare() {
        return adlBaseFare;
    }

    public void setAdlBaseFare(Object adlBaseFare) {
        this.adlBaseFare = adlBaseFare;
    }

    public Object getChdBaseFare() {
        return chdBaseFare;
    }

    public void setChdBaseFare(Object chdBaseFare) {
        this.chdBaseFare = chdBaseFare;
    }

    public Object getInfBaseFare() {
        return infBaseFare;
    }

    public void setInfBaseFare(Object infBaseFare) {
        this.infBaseFare = infBaseFare;
    }

    public Object getAdlTotalFare() {
        return adlTotalFare;
    }

    public void setAdlTotalFare(Object adlTotalFare) {
        this.adlTotalFare = adlTotalFare;
    }

    public Object getChdTotalFare() {
        return chdTotalFare;
    }

    public void setChdTotalFare(Object chdTotalFare) {
        this.chdTotalFare = chdTotalFare;
    }

    public Object getInfTotalFare() {
        return infTotalFare;
    }

    public void setInfTotalFare(Object infTotalFare) {
        this.infTotalFare = infTotalFare;
    }

    public Object getAdlCost() {
        return adlCost;
    }

    public void setAdlCost(Object adlCost) {
        this.adlCost = adlCost;
    }

    public Object getChdCost() {
        return chdCost;
    }

    public void setChdCost(Object chdCost) {
        this.chdCost = chdCost;
    }

    public Object getInfCost() {
        return infCost;
    }

    public void setInfCost(Object infCost) {
        this.infCost = infCost;
    }

    public Boolean getOneWay() {
        return oneWay;
    }

    public void setOneWay(Boolean oneWay) {
        this.oneWay = oneWay;
    }

    public Boolean getIsOpenjaw() {
        return isOpenjaw;
    }

    public void setIsOpenjaw(Boolean isOpenjaw) {
        this.isOpenjaw = isOpenjaw;
    }

    public Boolean getIsIntegration() {
        return isIntegration;
    }

    public void setIsIntegration(Boolean isIntegration) {
        this.isIntegration = isIntegration;
    }

    public List<SegmentList> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<SegmentList> segmentList) {
        this.segmentList = segmentList;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getNoteBaggage() {
        return noteBaggage;
    }

    public void setNoteBaggage(String noteBaggage) {
        this.noteBaggage = noteBaggage;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

}
