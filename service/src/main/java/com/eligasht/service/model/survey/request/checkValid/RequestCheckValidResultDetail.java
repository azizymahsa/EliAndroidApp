
package com.eligasht.service.model.survey.request.checkValid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCheckValidResultDetail {

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
