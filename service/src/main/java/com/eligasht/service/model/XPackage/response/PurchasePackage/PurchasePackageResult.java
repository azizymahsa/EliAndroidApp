
package com.eligasht.service.model.XPackage.response.PurchasePackage;

import java.lang.Error;
import java.util.List;

import com.eligasht.service.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchasePackageResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Services")
    @Expose
    private Object services;
    @SerializedName("TmpReserveResult")
    @Expose
    private TmpReserveResult tmpReserveResult;

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

    public Object getServices() {
        return services;
    }

    public void setServices(Object services) {
        this.services = services;
    }

    public TmpReserveResult getTmpReserveResult() {
        return tmpReserveResult;
    }

    public void setTmpReserveResult(TmpReserveResult tmpReserveResult) {
        this.tmpReserveResult = tmpReserveResult;
    }

}
