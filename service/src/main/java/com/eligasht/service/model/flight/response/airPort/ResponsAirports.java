
package com.eligasht.service.model.flight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsAirports {

    @SerializedName("GetAirportWithParentsWithCultureResult")
    @Expose
    private GetAirportWithParentsWithCultureResult getAirportWithParentsWithCultureResult;

    public GetAirportWithParentsWithCultureResult getGetAirportWithParentsWithCultureResult() {
        return getAirportWithParentsWithCultureResult;
    }

    public void setGetAirportWithParentsWithCultureResult(GetAirportWithParentsWithCultureResult getAirportWithParentsWithCultureResult) {
        this.getAirportWithParentsWithCultureResult = getAirportWithParentsWithCultureResult;
    }

}
