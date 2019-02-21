
package com.eligasht.service.model.newModel.xpackage.packageBasket.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetPackageBasket {

    @SerializedName("PackBasket")
    @Expose
    private PackBasket packBasket;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("SumPrice")
    @Expose
    private SumPrice sumPrice;
    @SerializedName("Errors")
    @Expose
    private List<Object> errors = null;
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

    public PackBasket getPackBasket() {
        return packBasket;
    }

    public void setPackBasket(PackBasket packBasket) {
        this.packBasket = packBasket;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public SumPrice getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(SumPrice sumPrice) {
        this.sumPrice = sumPrice;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
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
