
package com.eligasht.service.model.newModel.hotelFlight.preSearch.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomList {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("EId")
    @Expose
    private String eId;
    @SerializedName("HotelId")
    @Expose
    private String hotelId;
    @SerializedName("EHotelId")
    @Expose
    private String eHotelId;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("SourcePrice")
    @Expose
    private Double sourcePrice;
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
    @SerializedName("Board")
    @Expose
    private String board;
    @SerializedName("BoardID")
    @Expose
    private String boardID;
    @SerializedName("Load")
    @Expose
    private Integer load;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;
    @SerializedName("Source")
    @Expose
    private Integer source;
    @SerializedName("Summery")
    @Expose
    private String summery;
    @SerializedName("Rooms")
    @Expose
    private List<Object> rooms = null;
    @SerializedName("OfferId")
    @Expose
    private String offerId;
    @SerializedName("Errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("Warningss")
    @Expose
    private List<Object> warningss = null;
    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEHotelId() {
        return eHotelId;
    }

    public void setEHotelId(String eHotelId) {
        this.eHotelId = eHotelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSourcePrice() {
        return sourcePrice;
    }

    public void setSourcePrice(Double sourcePrice) {
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

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public List<Object> getRooms() {
        return rooms;
    }

    public void setRooms(List<Object> rooms) {
        this.rooms = rooms;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Object> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Object> warningss) {
        this.warningss = warningss;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
