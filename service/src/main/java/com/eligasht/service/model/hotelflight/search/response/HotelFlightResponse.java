
package com.eligasht.service.model.hotelflight.search.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelFlightResponse {

    @SerializedName("HotelFlightSearchResult")
    @Expose
    public HotelFlightSearchResult hotelFlightSearchResult;

    public HotelFlightSearchResult getHotelFlightSearchResult() {
        return hotelFlightSearchResult;
    }

    public void setHotelFlightSearchResult(HotelFlightSearchResult hotelFlightSearchResult) {
        this.hotelFlightSearchResult = hotelFlightSearchResult;
    }

}
