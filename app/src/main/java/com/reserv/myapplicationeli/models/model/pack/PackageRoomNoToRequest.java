package com.reserv.myapplicationeli.models.model.pack;

/**
 * Created by hoseinraeisi on 1/28/18.
 */

public class PackageRoomNoToRequest {

    private String passengerType;
    private int packRoomType_ID;
    private int room_No;


    public PackageRoomNoToRequest(String passengerType, int packRoomType_ID, int room_No) {
        this.passengerType = passengerType;
        this.packRoomType_ID = packRoomType_ID;
        this.room_No = room_No;
    }


    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public int getPackRoomType_ID() {
        return packRoomType_ID;
    }

    public void setPackRoomType_ID(int packRoomType_ID) {
        this.packRoomType_ID = packRoomType_ID;
    }

    public int getRoom_No() {
        return room_No;
    }

    public void setRoom_No(int room_No) {
        this.room_No = room_No;
    }
}
