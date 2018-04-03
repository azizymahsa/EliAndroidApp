
package com.eligasht.service.model.flight.response.searchFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsSearchFlight {

    @SerializedName("SearchFlightsResult")
    @Expose
    private SearchFlightsResult searchFlightsResult;

    public SearchFlightsResult getSearchFlightsResult() {
        return searchFlightsResult;
    }

    public void setSearchFlightsResult(SearchFlightsResult searchFlightsResult) {
        this.searchFlightsResult = searchFlightsResult;
    }

}
