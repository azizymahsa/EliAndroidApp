
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DropOff___ {

    @SerializedName("Address")
    @Expose
    private Object address;
    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("FligtNumber")
    @Expose
    private String fligtNumber;
    @SerializedName("MeetingPointType")
    @Expose
    private String meetingPointType;
    @SerializedName("Name")
    @Expose
    private Object name;

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFligtNumber() {
        return fligtNumber;
    }

    public void setFligtNumber(String fligtNumber) {
        this.fligtNumber = fligtNumber;
    }

    public String getMeetingPointType() {
        return meetingPointType;
    }

    public void setMeetingPointType(String meetingPointType) {
        this.meetingPointType = meetingPointType;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }



}
