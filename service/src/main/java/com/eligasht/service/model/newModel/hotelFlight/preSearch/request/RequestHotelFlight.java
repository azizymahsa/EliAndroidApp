
package com.eligasht.service.model.newModel.hotelFlight.preSearch.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHotelFlight {

    @SerializedName("QueryModel")
    @Expose
    private QueryModel queryModel;

    public QueryModel getQueryModel() {
        return queryModel;
    }

    public void setQueryModel(QueryModel queryModel) {
        this.queryModel = queryModel;
    }

}
