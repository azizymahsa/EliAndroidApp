
package com.eligasht.service.model.newModel.xpackage.packageBasket.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackSelectedRoomList {

    @SerializedName("RoomNo")
    @Expose
    private String roomNo;
    @SerializedName("PackRowRoomTypeID")
    @Expose
    private String packRowRoomTypeID;
    @SerializedName("RoomTitle")
    @Expose
    private String roomTitle;
    @SerializedName("RoomNameEn")
    @Expose
    private String roomNameEn;
    @SerializedName("RoomNameFa")
    @Expose
    private String roomNameFa;
    @SerializedName("RoomString")
    @Expose
    private String roomString;
    @SerializedName("Adults")
    @Expose
    private String adults;
    @SerializedName("Childs")
    @Expose
    private String childs;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getPackRowRoomTypeID() {
        return packRowRoomTypeID;
    }

    public void setPackRowRoomTypeID(String packRowRoomTypeID) {
        this.packRowRoomTypeID = packRowRoomTypeID;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomNameEn() {
        return roomNameEn;
    }

    public void setRoomNameEn(String roomNameEn) {
        this.roomNameEn = roomNameEn;
    }

    public String getRoomNameFa() {
        return roomNameFa;
    }

    public void setRoomNameFa(String roomNameFa) {
        this.roomNameFa = roomNameFa;
    }

    public String getRoomString() {
        return roomString;
    }

    public void setRoomString(String roomString) {
        this.roomString = roomString;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getChilds() {
        return childs;
    }

    public void setChilds(String childs) {
        this.childs = childs;
    }

}
