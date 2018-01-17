
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchasePackageResult {

    @SerializedName("Error")
    @Expose
    private Object error;
    @SerializedName("Services")
    @Expose
    private Object services;
    @SerializedName("TmpReserveResult")
    @Expose
    private TmpReserveResult tmpReserveResult;

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
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
