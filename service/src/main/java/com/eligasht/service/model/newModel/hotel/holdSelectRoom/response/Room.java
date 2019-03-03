
package com.eligasht.service.model.newModel.hotel.holdSelectRoom.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("RoomNo")
    @Expose
    private String roomNo;
    @SerializedName("ERoomNo")
    @Expose
    private String eRoomNo;
    @SerializedName("AdultCount")
    @Expose
    private Integer adultCount;
    @SerializedName("ChildCount")
    @Expose
    private Integer childCount;
    @SerializedName("InfantdCount")
    @Expose
    private Integer infantdCount;
    @SerializedName("ChildAges")
    @Expose
    private List<Object> childAges = null;
    @SerializedName("SourcePrice")
    @Expose
    private Integer sourcePrice;
    @SerializedName("OldPrice")
    @Expose
    private Integer oldPrice;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("CurrencyPrice")
    @Expose
    private Double currencyPrice;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("BoardID")
    @Expose
    private String boardID;
    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getERoomNo() {
        return eRoomNo;
    }

    public void setERoomNo(String eRoomNo) {
        this.eRoomNo = eRoomNo;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getInfantdCount() {
        return infantdCount;
    }

    public void setInfantdCount(Integer infantdCount) {
        this.infantdCount = infantdCount;
    }

    public List<Object> getChildAges() {
        return childAges;
    }

    public void setChildAges(List<Object> childAges) {
        this.childAges = childAges;
    }

    public Integer getSourcePrice() {
        return sourcePrice;
    }

    public void setSourcePrice(Integer sourcePrice) {
        this.sourcePrice = sourcePrice;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(Double currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

}
