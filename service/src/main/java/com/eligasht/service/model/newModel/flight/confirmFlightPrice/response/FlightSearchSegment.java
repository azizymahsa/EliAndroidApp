
package com.eligasht.service.model.newModel.flight.confirmFlightPrice.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightSearchSegment {

    @SerializedName("RowNumber")
    @Expose
    private Integer rowNumber;
    @SerializedName("SourceText")
    @Expose
    private Object sourceText;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("DestinationText")
    @Expose
    private Object destinationText;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("FlyDate")
    @Expose
    private String flyDate;

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Object getSourceText() {
        return sourceText;
    }

    public void setSourceText(Object sourceText) {
        this.sourceText = sourceText;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Object getDestinationText() {
        return destinationText;
    }

    public void setDestinationText(Object destinationText) {
        this.destinationText = destinationText;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlyDate() {
        return flyDate;
    }

    public void setFlyDate(String flyDate) {
        this.flyDate = flyDate;
    }

}
