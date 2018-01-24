
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.Errors;

import java.util.ArrayList;

public class SearchXPackageResult {

    @SerializedName("Errors")
    @Expose
    private ArrayList<Errors> error;
    @SerializedName("PRowXfers")
    @Expose
    private ArrayList<PRowXfer> PRowXfers = null;

    public ArrayList<Errors> getError() {
        return error;
    }

    public void setError(ArrayList<Errors> error) {
        this.error = error;
    }

    public ArrayList<PRowXfer> getPRowXfers() {
        return PRowXfers;
    }

    public void setPRowXfers(ArrayList<PRowXfer> pRowXfers) {
        this.PRowXfers = pRowXfers;
    }

}
