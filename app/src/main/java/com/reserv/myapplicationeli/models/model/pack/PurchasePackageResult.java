
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.Errors;

import java.util.ArrayList;

public class PurchasePackageResult {

    @SerializedName("Errors")
    @Expose
    private ArrayList<Errors> error;
    @SerializedName("Services")
    @Expose
    private Object services;
    @SerializedName("TmpReserveResult")
    @Expose
    private TmpReserveResult tmpReserveResult;

    public ArrayList<Errors> getError() {
        return error;
    }

    public void setError(ArrayList<Errors> error) {
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
