
package com.eligasht.service.model.hotel.hold.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoldRoomResponse {

    @SerializedName("HoldSelectedRoomResult")
    @Expose
    private HoldSelectedRoomResult holdSelectedRoomResult;

    public HoldSelectedRoomResult getHoldSelectedRoomResult() {
        return holdSelectedRoomResult;
    }

    public void setHoldSelectedRoomResult(HoldSelectedRoomResult holdSelectedRoomResult) {
        this.holdSelectedRoomResult = holdSelectedRoomResult;
    }

}
