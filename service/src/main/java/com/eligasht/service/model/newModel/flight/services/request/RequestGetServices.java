package com.eligasht.service.model.newModel.flight.services.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestGetServices {
    @SerializedName("PrefacorNo")
    @Expose
    private String prefacorNo;

    public String getPrefacorNo() {
        return prefacorNo;
    }

    public void setPrefacorNo(String prefacorNo) {
        this.prefacorNo = prefacorNo;
    }
}
