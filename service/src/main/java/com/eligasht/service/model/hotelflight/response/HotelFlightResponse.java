
package com.eligasht.service.model.hotelflight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelFlightResponse {

    @SerializedName("HotelFlightSearchResult")
    @Expose
    private HotelFlightSearchResult hotelFlightSearchResult;

    public HotelFlightSearchResult getHotelFlightSearchResult() {
        return hotelFlightSearchResult;
    }

    public void setHotelFlightSearchResult(HotelFlightSearchResult hotelFlightSearchResult) {
        this.hotelFlightSearchResult = hotelFlightSearchResult;
    }

}
