package com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Train {
    @SerializedName("FareTypeID")
    @Expose
    private Integer FareTypeID ;

    @SerializedName("TrainID")
    @Expose
    private String TrainID ;

    @SerializedName("AccountID")
    @Expose
    private String AccountID ;

    @SerializedName("TrainTypeID")
    @Expose
    private Integer TrainTypeID ;

    @SerializedName("TrainGUID")
    @Expose
    private  String TrainGUID ;

    @SerializedName("Taxes")
    @Expose
    private Cost Taxes ;

    @SerializedName("BaseFare")
    @Expose
    private Cost BaseFare ;

    @SerializedName("TotalFare")
    @Expose
    private Cost TotalFare ;

    @SerializedName("TotalFareCost")
    @Expose
    private Cost TotalFareCost ;

    @SerializedName("AdlBaseFare")
    @Expose
    private Cost AdlBaseFare ;

    @SerializedName("ChdBaseFare")
    @Expose
    private Cost ChdBaseFare ;

    @SerializedName("InfBaseFare")
    @Expose
    private Cost InfBaseFare ;

    @SerializedName("AdlTotalFare")
    @Expose
    private Cost AdlTotalFare ;

    @SerializedName("ChdTotalFare")
    @Expose
    private Cost ChdTotalFare ;

    @SerializedName("InfTotalFare")
    @Expose
    private Cost  InfTotalFare ;

    @SerializedName("AdlCost")
    @Expose
    private Cost AdlCost ;

    @SerializedName("ChdCost")
    @Expose
    private  Cost  ChdCost ;

    @SerializedName("InfCost")
    @Expose
    private  Cost InfCost ;

    @SerializedName("OneWay")
    @Expose
    private  Boolean OneWay ;

    @SerializedName("IsOpenjaw")
    @Expose
    private  Boolean IsOpenjaw ;

    @SerializedName("IsIntegration")
    @Expose
    private  Boolean IsIntegration ;

    @SerializedName("SegmentList")
    @Expose
    private List<SegmentList> SegmentList;

    @SerializedName("Passengers")
    @Expose
    private String Passengers;

    @SerializedName("NoteBaggage")
    @Expose
    private String NoteBaggage ;

    public Integer getFareTypeID() {
        return FareTypeID;
    }

    public void setFareTypeID(Integer fareTypeID) {
        FareTypeID = fareTypeID;
    }

    public String getTrainID() {
        return TrainID;
    }

    public void setTrainID(String trainID) {
        TrainID = trainID;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public Integer getTrainTypeID() {
        return TrainTypeID;
    }

    public void setTrainTypeID(Integer trainTypeID) {
        TrainTypeID = trainTypeID;
    }

    public String getTrainGUID() {
        return TrainGUID;
    }

    public void setTrainGUID(String trainGUID) {
        TrainGUID = trainGUID;
    }

    public Cost getTaxes() {
        return Taxes;
    }

    public void setTaxes(Cost taxes) {
        Taxes = taxes;
    }

    public Cost getBaseFare() {
        return BaseFare;
    }

    public void setBaseFare(Cost baseFare) {
        BaseFare = baseFare;
    }

    public Cost getTotalFare() {
        return TotalFare;
    }

    public void setTotalFare(Cost totalFare) {
        TotalFare = totalFare;
    }

    public Cost getTotalFareCost() {
        return TotalFareCost;
    }

    public void setTotalFareCost(Cost totalFareCost) {
        TotalFareCost = totalFareCost;
    }

    public Cost getAdlBaseFare() {
        return AdlBaseFare;
    }

    public void setAdlBaseFare(Cost adlBaseFare) {
        AdlBaseFare = adlBaseFare;
    }

    public Cost getChdBaseFare() {
        return ChdBaseFare;
    }

    public void setChdBaseFare(Cost chdBaseFare) {
        ChdBaseFare = chdBaseFare;
    }

    public Cost getInfBaseFare() {
        return InfBaseFare;
    }

    public void setInfBaseFare(Cost infBaseFare) {
        InfBaseFare = infBaseFare;
    }

    public Cost getAdlTotalFare() {
        return AdlTotalFare;
    }

    public void setAdlTotalFare(Cost adlTotalFare) {
        AdlTotalFare = adlTotalFare;
    }

    public Cost getChdTotalFare() {
        return ChdTotalFare;
    }

    public void setChdTotalFare(Cost chdTotalFare) {
        ChdTotalFare = chdTotalFare;
    }

    public Cost getInfTotalFare() {
        return InfTotalFare;
    }

    public void setInfTotalFare(Cost infTotalFare) {
        InfTotalFare = infTotalFare;
    }

    public Cost getAdlCost() {
        return AdlCost;
    }

    public void setAdlCost(Cost adlCost) {
        AdlCost = adlCost;
    }

    public Cost getChdCost() {
        return ChdCost;
    }

    public void setChdCost(Cost chdCost) {
        ChdCost = chdCost;
    }

    public Cost getInfCost() {
        return InfCost;
    }

    public void setInfCost(Cost infCost) {
        InfCost = infCost;
    }

    public Boolean getOneWay() {
        return OneWay;
    }

    public void setOneWay(Boolean oneWay) {
        OneWay = oneWay;
    }

    public Boolean getOpenjaw() {
        return IsOpenjaw;
    }

    public void setOpenjaw(Boolean openjaw) {
        IsOpenjaw = openjaw;
    }

    public Boolean getIntegration() {
        return IsIntegration;
    }

    public void setIntegration(Boolean integration) {
        IsIntegration = integration;
    }

    public List<com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response.SegmentList> getSegmentList() {
        return SegmentList;
    }

    public void setSegmentList(List<com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response.SegmentList> segmentList) {
        SegmentList = segmentList;
    }

    public String getPassengers() {
        return Passengers;
    }

    public void setPassengers(String passengers) {
        Passengers = passengers;
    }

    public String getNoteBaggage() {
        return NoteBaggage;
    }

    public void setNoteBaggage(String noteBaggage) {
        NoteBaggage = noteBaggage;
    }
}
