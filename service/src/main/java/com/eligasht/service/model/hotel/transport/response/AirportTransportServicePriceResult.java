
package com.eligasht.service.model.hotel.transport.response;

import java.util.List;

import com.eligasht.service.model.BaseModel;
import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirportTransportServicePriceResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("TransferAvailabilityRoundtripResults")
    @Expose
    private List<TransferAvailabilityRoundtripResult> transferAvailabilityRoundtripResults = null;

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

    public List<TransferAvailabilityRoundtripResult> getTransferAvailabilityRoundtripResults() {
        return transferAvailabilityRoundtripResults;
    }

    public void setTransferAvailabilityRoundtripResults(List<TransferAvailabilityRoundtripResult> transferAvailabilityRoundtripResults) {
        this.transferAvailabilityRoundtripResults = transferAvailabilityRoundtripResults;
    }

}
