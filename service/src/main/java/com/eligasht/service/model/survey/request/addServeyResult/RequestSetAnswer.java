
package com.eligasht.service.model.survey.request.addServeyResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSetAnswer {

    @SerializedName("request")
    @Expose
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

}
