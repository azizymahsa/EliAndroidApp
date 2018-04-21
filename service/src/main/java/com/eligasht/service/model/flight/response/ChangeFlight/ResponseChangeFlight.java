
package com.eligasht.service.model.flight.response.ChangeFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseChangeFlight {

    @SerializedName("HotelPlusFlightChangeFltResult")
    @Expose
    public HotelPlusFlightChangeFltResult hotelPlusFlightChangeFltResult;

    public HotelPlusFlightChangeFltResult getHotelPlusFlightChangeFltResult() {
        return hotelPlusFlightChangeFltResult;
    }

    public void setHotelPlusFlightChangeFltResult(HotelPlusFlightChangeFltResult hotelPlusFlightChangeFltResult) {
        this.hotelPlusFlightChangeFltResult = hotelPlusFlightChangeFltResult;
    }

}
