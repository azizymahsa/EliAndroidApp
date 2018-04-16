
package com.eligasht.reservation.models.model.insurance.call;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsuranceRequestModel {

    @SerializedName("request")
    @Expose
    private InsuranceListReq request;

    public InsuranceRequestModel(InsuranceListReq request) {
        Log.e("InsuranceRequestModel: ", new Gson().toJson(request));
        this.request = request;
    }

    public InsuranceListReq getRequest() {
        return request;
    }

    public void setRequest(InsuranceListReq request) {
        Log.e("InsuranceRequestModel: ", new Gson().toJson(request));
        this.request = request;
    }

}
