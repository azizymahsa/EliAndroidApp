
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import java.util.List;

import com.eligasht.reservation.models.model.Errors;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AirportTransportServicePriceResult {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Errors")
    @Expose
    private List<com.eligasht.reservation.models.hotel.api.Errors> errors = null;
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

    public List<com.eligasht.reservation.models.hotel.api.Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<com.eligasht.reservation.models.hotel.api.Errors> errors) {
        this.errors = errors;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("comments", comments).append("errors", errors).append("resultKey", resultKey).append("warningss", warningss).append("transferAvailabilityRoundtripResults", transferAvailabilityRoundtripResults).toString();
    }

}
