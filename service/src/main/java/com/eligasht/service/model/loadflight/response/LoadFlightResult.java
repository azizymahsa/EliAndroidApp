
package com.eligasht.service.model.loadflight.response;

import com.eligasht.service.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class LoadFlightResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("HFlight")
    @Expose
    private HFlight hFlight;

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

    public HFlight getHFlight() {
        return hFlight;
    }

    public void setHFlight(HFlight hFlight) {
        this.hFlight = hFlight;
    }

}
