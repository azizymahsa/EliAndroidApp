
package com.eligasht.service.model.hotelpolicy.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HCancellationPolicy_ {

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("DescriptionEn")
    @Expose
    private Object descriptionEn;
    @SerializedName("DescriptionFa")
    @Expose
    private Object descriptionFa;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("FromDate_d")
    @Expose
    private String fromDateD;
    @SerializedName("ReturnAmount")
    @Expose
    private Double returnAmount;
    @SerializedName("RoomNo")
    @Expose
    private String roomNo;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("ToDate_d")
    @Expose
    private String toDateD;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(Object descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Object getDescriptionFa() {
        return descriptionFa;
    }

    public void setDescriptionFa(Object descriptionFa) {
        this.descriptionFa = descriptionFa;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDateD() {
        return fromDateD;
    }

    public void setFromDateD(String fromDateD) {
        this.fromDateD = fromDateD;
    }

    public Double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDateD() {
        return toDateD;
    }

    public void setToDateD(String toDateD) {
        this.toDateD = toDateD;
    }

}
