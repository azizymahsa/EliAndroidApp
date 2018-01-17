package com.reserv.myapplicationeli.models.model.pack.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by elham.bonyani on 1/6/18.
 */

public class CountryListRes {

    @SerializedName("GetCountryAjaxResult")
    @Expose
    private com.reserv.myapplicationeli.models.model.pack.GetCountryAjaxResult GetCountryAjaxResult;

    public com.reserv.myapplicationeli.models.model.pack.GetCountryAjaxResult getCountryAjaxResult() {
        return GetCountryAjaxResult;
    }

    public void setCountryAjaxResult(com.reserv.myapplicationeli.models.model.pack.GetCountryAjaxResult getCountryAjaxResult) {
        this.GetCountryAjaxResult = getCountryAjaxResult;
    }
}
