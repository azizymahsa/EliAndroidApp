
package com.eligasht.service.model.hotel.hotelAvail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RoomList {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Errors")
    @Expose
    private Object errors;
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
    private double currencyPrice;
    @SerializedName("Description")
    @Expose
    private Object description;
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
    private boolean isSelected;
    @SerializedName("Load")
    @Expose
    private int load;
    @SerializedName("OfferId")
    @Expose
    private String offerId;
    @SerializedName("OldPrice")
    @Expose
    private int oldPrice;
    @SerializedName("Price")
    @Expose
    private int price;
    @SerializedName("Rooms")
    @Expose
    private List<Object> rooms = null;
    @SerializedName("Source")
    @Expose
    private int source;
    @SerializedName("SourcePrice")
    @Expose
    private int sourcePrice;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Summery")
    @Expose
    private Object summery;
    @SerializedName("Title")
    @Expose
    private String title;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
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

    public double getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(double currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
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

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Object> getRooms() {
        return rooms;
    }

    public void setRooms(List<Object> rooms) {
        this.rooms = rooms;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getSourcePrice() {
        return sourcePrice;
    }

    public void setSourcePrice(int sourcePrice) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}


}
