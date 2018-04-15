
package com.eligasht.service.model.hotel.room.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("AdultCount")
    @Expose
    private Integer adultCount;
    @SerializedName("BoardID")
    @Expose
    private String boardID;
    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("ChildAges")
    @Expose
    private List<Object> childAges = null;
    @SerializedName("ChildCount")
    @Expose
    private Integer childCount;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("CurrencyPrice")
    @Expose
    private Double currencyPrice;
    @SerializedName("ERoomNo")
    @Expose
    private String eRoomNo;
    @SerializedName("OldPrice")
    @Expose
    private Integer oldPrice;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("RoomNo")
    @Expose
    private String roomNo;
    @SerializedName("SourcePrice")
    @Expose
    private Double sourcePrice;
    @SerializedName("Title")
    @Expose
    private String title;

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public String getBoardID() {
        return boardID;
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public List<Object> getChildAges() {
        return childAges;
    }

    public void setChildAges(List<Object> childAges) {
        this.childAges = childAges;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(Double currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public String getERoomNo() {
        return eRoomNo;
    }

    public void setERoomNo(String eRoomNo) {
        this.eRoomNo = eRoomNo;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Double getSourcePrice() {
        return sourcePrice;
    }

    public void setSourcePrice(Double sourcePrice) {
        this.sourcePrice = sourcePrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
