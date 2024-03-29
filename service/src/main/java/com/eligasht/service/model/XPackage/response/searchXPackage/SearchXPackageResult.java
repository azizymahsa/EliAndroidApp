
package com.eligasht.service.model.XPackage.response.searchXPackage;

import java.lang.Error;
import java.util.List;

import com.eligasht.service.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchXPackageResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("PRowXfers")
    @Expose
    private List<PRowXfer> pRowXfers = null;

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

    public List<PRowXfer> getPRowXfers() {
        return pRowXfers;
    }

    public void setPRowXfers(List<PRowXfer> pRowXfers) {
        this.pRowXfers = pRowXfers;
    }

}
