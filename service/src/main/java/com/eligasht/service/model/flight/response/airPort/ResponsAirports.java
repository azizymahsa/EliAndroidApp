
package com.eligasht.service.model.flight.response.airPort;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsAirports {

    @SerializedName("GetAirportWithParentsWithCultureResult")
    @Expose
    public GetAirportWithParentsWithCultureResult getAirportWithParentsWithCultureResult;

    public GetAirportWithParentsWithCultureResult getGetAirportWithParentsWithCultureResult() {
        return getAirportWithParentsWithCultureResult;
    }

    public void setGetAirportWithParentsWithCultureResult(GetAirportWithParentsWithCultureResult getAirportWithParentsWithCultureResult) {
        this.getAirportWithParentsWithCultureResult = getAirportWithParentsWithCultureResult;
    }

}
