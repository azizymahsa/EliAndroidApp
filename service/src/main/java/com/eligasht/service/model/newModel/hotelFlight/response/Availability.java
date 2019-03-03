
package com.eligasht.service.model.newModel.hotelFlight.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Availability {

    @SerializedName("RoomLists")
    @Expose
    private List<RoomList> roomLists = null;

    public List<RoomList> getRoomLists() {
        return roomLists;
    }

    public void setRoomLists(List<RoomList> roomLists) {
        this.roomLists = roomLists;
    }

}
