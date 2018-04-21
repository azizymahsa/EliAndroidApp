
package com.eligasht.service.model.hotelpolicy.response;

import java.util.List;

import com.eligasht.service.model.BaseModel;
import com.eligasht.service.model.hotel.hotelAvail.response.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelPolicyResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("HCancellationPolicies")
    @Expose
    private List<HCancellationPolicy> hCancellationPolicies = null;

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

    public List<HCancellationPolicy> getHCancellationPolicies() {
        return hCancellationPolicies;
    }

    public void setHCancellationPolicies(List<HCancellationPolicy> hCancellationPolicies) {
        this.hCancellationPolicies = hCancellationPolicies;
    }

}
