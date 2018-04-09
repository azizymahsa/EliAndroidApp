
package com.eligasht.service.model.insurance.request.RequestPreFactorDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("invoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
