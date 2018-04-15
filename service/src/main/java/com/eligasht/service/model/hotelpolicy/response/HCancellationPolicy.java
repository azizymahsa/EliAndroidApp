
package com.eligasht.service.model.hotelpolicy.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HCancellationPolicy {

    @SerializedName("HCancellationPolicy")
    @Expose
    private List<HCancellationPolicy_> hCancellationPolicy = null;
    @SerializedName("Key")
    @Expose
    private String key;

    public List<HCancellationPolicy_> getHCancellationPolicy() {
        return hCancellationPolicy;
    }

    public void setHCancellationPolicy(List<HCancellationPolicy_> hCancellationPolicy) {
        this.hCancellationPolicy = hCancellationPolicy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
