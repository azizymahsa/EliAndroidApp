
package com.eligasht.service.model.hotel.room.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRoomResponse {

    @SerializedName("GetRoomsListResult")
    @Expose
    private GetRoomsListResult getRoomsListResult;

    public GetRoomsListResult getGetRoomsListResult() {
        return getRoomsListResult;
    }

    public void setGetRoomsListResult(GetRoomsListResult getRoomsListResult) {
        this.getRoomsListResult = getRoomsListResult;
    }

}
