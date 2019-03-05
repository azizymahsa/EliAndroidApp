
package com.eligasht.service.model.newModel.flight.services.response;

import java.util.List;

import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetServices {

    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private List<Object> warningss = null;
    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private String resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("ExcursionDetails")
    @Expose
    private ExcursionDetails excursionDetails;
    @SerializedName("Passengers")
    @Expose
    private List<Passenger> passengers = null;
    @SerializedName("Services")
    @Expose
    private List<Service> services = null;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Object> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Object> warningss) {
        this.warningss = warningss;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ExcursionDetails getExcursionDetails() {
        return excursionDetails;
    }

    public void setExcursionDetails(ExcursionDetails excursionDetails) {
        this.excursionDetails = excursionDetails;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}
