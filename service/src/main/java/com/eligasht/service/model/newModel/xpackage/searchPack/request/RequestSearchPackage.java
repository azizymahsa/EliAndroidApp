
package com.eligasht.service.model.newModel.xpackage.searchPack.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSearchPackage {

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
