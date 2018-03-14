package com.eligasht.reservation.models.hotel.api.hotelPolicy.test;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class HCancellationPolicy {
    private String ToDate;

    private String RoomNo;

    private String ReturnAmount;

    private String FromDate;

    private String DescriptionFa;

    private String Currency;

    private String DescriptionEn;
    private String FromDate_d;

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String RoomNo) {
        this.RoomNo = RoomNo;
    }

    public String getReturnAmount() {
        return ReturnAmount;
    }

    public void setReturnAmount(String ReturnAmount) {
        this.ReturnAmount = ReturnAmount;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    public String getDescriptionFa() {
        return DescriptionFa;
    }

    public void setDescriptionFa(String DescriptionFa) {
        this.DescriptionFa = DescriptionFa;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getDescriptionEn() {
        return DescriptionEn;
    }

    public void setDescriptionEn(String DescriptionEn) {
        this.DescriptionEn = DescriptionEn;
    }

    public String getFromDate_d() {
        return FromDate_d;
    }

    public void setFromDate_d(String fromDate_d) {
        FromDate_d = fromDate_d;
    }

    @Override
    public String toString() {
        return "ClassPojo [ToDate = " + ToDate + ", RoomNo = " + RoomNo + ", ReturnAmount = " + ReturnAmount + ", FromDate = " + FromDate + ", DescriptionFa = " + DescriptionFa + ", Currency = " + Currency + ", DescriptionEn = " + DescriptionEn + "]";
    }
}