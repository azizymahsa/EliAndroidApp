
package com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDomesticTrainGetPrice {

    @SerializedName("Trains")
    @Expose
    private List<Train> trains = null;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("QueryString")
    @Expose
    private Object queryString;
    @SerializedName("SearchedProduct")
    @Expose
    private Object searchedProduct;
    @SerializedName("ResultFilter")
    @Expose
    private List<Object> resultFilter = null;
    @SerializedName("TrainSearched")
    @Expose
    private Object trainSearched;
    @SerializedName("Passengers")
    @Expose
    private List<Object> passengers = null;
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
    private Object resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Object getQueryString() {
        return queryString;
    }

    public void setQueryString(Object queryString) {
        this.queryString = queryString;
    }

    public Object getSearchedProduct() {
        return searchedProduct;
    }

    public void setSearchedProduct(Object searchedProduct) {
        this.searchedProduct = searchedProduct;
    }

    public List<Object> getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(List<Object> resultFilter) {
        this.resultFilter = resultFilter;
    }

    public Object getTrainSearched() {
        return trainSearched;
    }

    public void setTrainSearched(Object trainSearched) {
        this.trainSearched = trainSearched;
    }

    public List<Object> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Object> passengers) {
        this.passengers = passengers;
    }

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

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
