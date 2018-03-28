
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DropOff {

    @SerializedName("Address")
    @Expose
    private Object address;
    @SerializedName("CityCode")
    @Expose
    private Object cityCode;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("FligtNumber")
    @Expose
    private Object fligtNumber;
    @SerializedName("MeetingPointType")
    @Expose
    private String meetingPointType;
    @SerializedName("Name")
    @Expose
    private String name;

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCityCode() {
        return cityCode;
    }

    public void setCityCode(Object cityCode) {
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

    public Object getFligtNumber() {
        return fligtNumber;
    }

    public void setFligtNumber(Object fligtNumber) {
        this.fligtNumber = fligtNumber;
    }

    public String getMeetingPointType() {
        return meetingPointType;
    }

    public void setMeetingPointType(String meetingPointType) {
        this.meetingPointType = meetingPointType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("address", address).append("cityCode", cityCode).append("code", code).append("dateTime", dateTime).append("fligtNumber", fligtNumber).append("meetingPointType", meetingPointType).append("name", name).toString();
    }

}
