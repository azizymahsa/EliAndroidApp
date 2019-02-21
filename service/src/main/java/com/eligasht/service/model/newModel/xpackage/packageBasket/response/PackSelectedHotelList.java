
package com.eligasht.service.model.newModel.xpackage.packageBasket.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackSelectedHotelList {

    @SerializedName("HotelID")
    @Expose
    private Object hotelID;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("HotelStar")
    @Expose
    private String hotelStar;
    @SerializedName("HotelAddress")
    @Expose
    private String hotelAddress;
    @SerializedName("HotelCity")
    @Expose
    private String hotelCity;
    @SerializedName("HotelImgPath")
    @Expose
    private String hotelImgPath;
    @SerializedName("AdlCount")
    @Expose
    private Integer adlCount;
    @SerializedName("ChdCount")
    @Expose
    private Integer chdCount;
    @SerializedName("RoomCount")
    @Expose
    private Integer roomCount;
    @SerializedName("RoomName")
    @Expose
    private String roomName;
    @SerializedName("CheckIn")
    @Expose
    private String checkIn;
    @SerializedName("CheckOut")
    @Expose
    private String checkOut;
    @SerializedName("Location")
    @Expose
    private String location;

    public Object getHotelID() {
        return hotelID;
    }

    public void setHotelID(Object hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelImgPath() {
        return hotelImgPath;
    }

    public void setHotelImgPath(String hotelImgPath) {
        this.hotelImgPath = hotelImgPath;
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

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
