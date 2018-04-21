
package com.eligasht.service.model.flight.response.purchaseServiceFlight;

import java.util.List;

import com.eligasht.service.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseServiceResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("ResultText")
    @Expose
    private String resultText;
    @SerializedName("SuccessResult")
    @Expose
    private Integer successResult;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }



    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public Integer getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(Integer successResult) {
        this.successResult = successResult;
    }

}
