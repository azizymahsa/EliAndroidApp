
package com.eligasht.service.model.newModel.train.domesticSearch.response;

import java.util.List;

import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDomesticTrainAPI {

    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("SearchedProduct")
    @Expose
    private SearchedProduct searchedProduct;
    @SerializedName("Trains")
    @Expose
    private List<Train> trains = null;
    @SerializedName("QueryString")
    @Expose
    private String queryString;
    @SerializedName("ResultFilter")
    @Expose
    private List<ResultFilter> resultFilter = null;
    @SerializedName("TrainSearched")
    @Expose
    private TrainSearched trainSearched;
    @SerializedName("Passengers")
    @Expose
    private List<Object> passengers = null;
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

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public SearchedProduct getSearchedProduct() {
        return searchedProduct;
    }

    public void setSearchedProduct(SearchedProduct searchedProduct) {
        this.searchedProduct = searchedProduct;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public List<ResultFilter> getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(List<ResultFilter> resultFilter) {
        this.resultFilter = resultFilter;
    }

    public TrainSearched getTrainSearched() {
        return trainSearched;
    }

    public void setTrainSearched(TrainSearched trainSearched) {
        this.trainSearched = trainSearched;
    }

    public List<Object> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Object> passengers) {
        this.passengers = passengers;
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
