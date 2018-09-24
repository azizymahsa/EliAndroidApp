
package com.eligasht.service.model.survey.response.checkValid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCheckValidResultDetail {

    @SerializedName("CheckValidResultDetailResult")
    @Expose
    private CheckValidResultDetailResult checkValidResultDetailResult;

    public CheckValidResultDetailResult getCheckValidResultDetailResult() {
        return checkValidResultDetailResult;
    }

    public void setCheckValidResultDetailResult(CheckValidResultDetailResult checkValidResultDetailResult) {
        this.checkValidResultDetailResult = checkValidResultDetailResult;
    }

}
