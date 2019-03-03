
package com.eligasht.service.model.newModel.flight.prefactor.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {

    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("RqHotelID")
    @Expose
    private Integer rqHotelID;
    @SerializedName("RoomID")
    @Expose
    private Integer roomID;
    @SerializedName("RqBaseID")
    @Expose
    private Integer rqBaseID;
    @SerializedName("BookingID")
    @Expose
    private String bookingID;
    @SerializedName("RoomCost")
    @Expose
    private String roomCost;
    @SerializedName("CurrencyCode")
    @Expose
    private Object currencyCode;
    @SerializedName("Supplier")
    @Expose
    private String supplier;
    @SerializedName("RoomNo")
    @Expose
    private Integer roomNo;
    @SerializedName("HotelNameE")
    @Expose
    private String hotelNameE;
    @SerializedName("HotelStarRating")
    @Expose
    private String hotelStarRating;
    @SerializedName("CityEn")
    @Expose
    private String cityEn;
    @SerializedName("CityFa")
    @Expose
    private String cityFa;
    @SerializedName("RoomTitleEn")
    @Expose
    private String roomTitleEn;
    @SerializedName("RoomTitleFa")
    @Expose
    private String roomTitleFa;
    @SerializedName("HotelChekin")
    @Expose
    private String hotelChekin;
    @SerializedName("HotelChekout")
    @Expose
    private String hotelChekout;
    @SerializedName("HotelBoardCode")
    @Expose
    private String hotelBoardCode;
    @SerializedName("NightsCount")
    @Expose
    private Integer nightsCount;
    @SerializedName("HotelLocationEn")
    @Expose
    private String hotelLocationEn;
    @SerializedName("RoomPrice")
    @Expose
    private Integer roomPrice;
    @SerializedName("TotalFltPrice")
    @Expose
    private TotalFltPrice totalFltPrice;
    @SerializedName("AdlCount")
    @Expose
    private Integer adlCount;
    @SerializedName("ChdCount")
    @Expose
    private Integer chdCount;
    @SerializedName("InfCount")
    @Expose
    private Integer infCount;
    @SerializedName("PassengersList")
    @Expose
    private Object passengersList;

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getRqHotelID() {
        return rqHotelID;
    }

    public void setRqHotelID(Integer rqHotelID) {
        this.rqHotelID = rqHotelID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getRqBaseID() {
        return rqBaseID;
    }

    public void setRqBaseID(Integer rqBaseID) {
        this.rqBaseID = rqBaseID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getRoomCost() {
        return roomCost;
    }

    public void setRoomCost(String roomCost) {
        this.roomCost = roomCost;
    }

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getHotelNameE() {
        return hotelNameE;
    }

    public void setHotelNameE(String hotelNameE) {
        this.hotelNameE = hotelNameE;
    }

    public String getHotelStarRating() {
        return hotelStarRating;
    }

    public void setHotelStarRating(String hotelStarRating) {
        this.hotelStarRating = hotelStarRating;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityFa() {
        return cityFa;
    }

    public void setCityFa(String cityFa) {
        this.cityFa = cityFa;
    }

    public String getRoomTitleEn() {
        return roomTitleEn;
    }

    public void setRoomTitleEn(String roomTitleEn) {
        this.roomTitleEn = roomTitleEn;
    }

    public String getRoomTitleFa() {
        return roomTitleFa;
    }

    public void setRoomTitleFa(String roomTitleFa) {
        this.roomTitleFa = roomTitleFa;
    }

    public String getHotelChekin() {
        return hotelChekin;
    }

    public void setHotelChekin(String hotelChekin) {
        this.hotelChekin = hotelChekin;
    }

    public String getHotelChekout() {
        return hotelChekout;
    }

    public void setHotelChekout(String hotelChekout) {
        this.hotelChekout = hotelChekout;
    }

    public String getHotelBoardCode() {
        return hotelBoardCode;
    }

    public void setHotelBoardCode(String hotelBoardCode) {
        this.hotelBoardCode = hotelBoardCode;
    }

    public Integer getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(Integer nightsCount) {
        this.nightsCount = nightsCount;
    }

    public String getHotelLocationEn() {
        return hotelLocationEn;
    }

    public void setHotelLocationEn(String hotelLocationEn) {
        this.hotelLocationEn = hotelLocationEn;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public TotalFltPrice getTotalFltPrice() {
        return totalFltPrice;
    }

    public void setTotalFltPrice(TotalFltPrice totalFltPrice) {
        this.totalFltPrice = totalFltPrice;
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

    public Integer getInfCount() {
        return infCount;
    }

    public void setInfCount(Integer infCount) {
        this.infCount = infCount;
    }

    public Object getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(Object passengersList) {
        this.passengersList = passengersList;
    }

}
