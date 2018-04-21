
package com.eligasht.service.model.hotel.hold.response;

import java.util.List;

import com.eligasht.service.model.BaseModel;
import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoldSelectedRoomResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Board")
    @Expose
    private String board;
    @SerializedName("BoardID")
    @Expose
    private String boardID;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("CurrencyPrice")
    @Expose
    private Double currencyPrice;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("EHotelId")
    @Expose
    private String eHotelId;
    @SerializedName("EId")
    @Expose
    private String eId;
    @SerializedName("HotelId")
    @Expose
    private String hotelId;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;
    @SerializedName("Load")
    @Expose
    private Integer load;
    @SerializedName("OfferId")
    @Expose
    private String offerId;
    @SerializedName("OldPrice")
    @Expose
    private Integer oldPrice;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("Rooms")
    @Expose
    private List<Room> rooms = null;
    @SerializedName("Source")
    @Expose
    private Integer source;
    @SerializedName("SourcePrice")
    @Expose
    private Double sourcePrice;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Summery")
    @Expose
    private Object summery;
    @SerializedName("Title")
    @Expose
    private Object title;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }


    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBoardID() {
        return boardID;
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEHotelId() {
        return eHotelId;
    }

    public void setEHotelId(String eHotelId) {
        this.eHotelId = eHotelId;
    }

    public String getEId() {
        return eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Double getSourcePrice() {
        return sourcePrice;
    }

    public void setSourcePrice(Double sourcePrice) {
        this.sourcePrice = sourcePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getSummery() {
        return summery;
    }

    public void setSummery(Object summery) {
        this.summery = summery;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

}
