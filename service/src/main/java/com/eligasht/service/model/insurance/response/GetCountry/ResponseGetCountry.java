
package com.eligasht.service.model.insurance.response.GetCountry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetCountry {

    @SerializedName("GetCountryAjaxWithCultureResult")
    @Expose
    public GetCountryAjaxWithCultureResult getCountryAjaxWithCultureResult;

    public GetCountryAjaxWithCultureResult getGetCountryAjaxWithCultureResult() {
        return getCountryAjaxWithCultureResult;
    }

    public void setGetCountryAjaxWithCultureResult(GetCountryAjaxWithCultureResult getCountryAjaxWithCultureResult) {
        this.getCountryAjaxWithCultureResult = getCountryAjaxWithCultureResult;
    }

}
