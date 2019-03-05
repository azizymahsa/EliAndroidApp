
package com.eligasht.service.model.newModel.flight.searchFlight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSearchFlight {
    @SerializedName("QueryModel")
    @Expose
    public QueryModel QueryModel;

    public QueryModel getQueryModel() {
        return QueryModel;
    }

    public void setQueryModel(QueryModel queryModel) {
        QueryModel = queryModel;
    }

}