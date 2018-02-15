package com.eligasht.reservation.models.model.pack.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.model.pack.GetPackageRoutesResult;

/**
 * Created by elham.bonyani on 1/6/18.
 */

public class CityListRes {

    @SerializedName("GetPackageRoutesResult")
    @Expose
    private com.eligasht.reservation.models.model.pack.GetPackageRoutesResult GetPackageRoutesResult;

    public GetPackageRoutesResult getGetHotelListResult() {
        return GetPackageRoutesResult;
    }

    public void setGetPackageRoutesResult(GetPackageRoutesResult getHotelListResult) {
        this.GetPackageRoutesResult = getHotelListResult;
    }
}
