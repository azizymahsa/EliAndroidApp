
package com.eligasht.service.model.newModel.xpackage.searchPack.response;

import java.util.List;

import com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSearchPackage {

    @SerializedName("PRowXfers")
    @Expose
    private List<PRowXfer> pRowXfers = null;
    @SerializedName("ResultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("ResultFilter")
    @Expose
    private List<ResultFilter> resultFilter = null;
    @SerializedName("QueryString")
    @Expose
    private String queryString;
    @SerializedName("SearchedProduct")
    @Expose
    private SearchedProduct searchedProduct;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

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

    public List<PRowXfer> getPRowXfers() {
        return pRowXfers;
    }

    public void setPRowXfers(List<PRowXfer> pRowXfers) {
        this.pRowXfers = pRowXfers;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<ResultFilter> getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(List<ResultFilter> resultFilter) {
        this.resultFilter = resultFilter;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public SearchedProduct getSearchedProduct() {
        return searchedProduct;
    }

    public void setSearchedProduct(SearchedProduct searchedProduct) {
        this.searchedProduct = searchedProduct;
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
