
package com.eligasht.service.model.hotelflight.purchase.request.PishFactor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("RqBaseID")
    @Expose
    private String rqBaseID;
    @SerializedName("ServiceStr")
    @Expose
    private String serviceStr;
    @SerializedName("Exc")
    @Expose
    private String exc;
    @SerializedName("InsCoverageXML")
    @Expose
    private String insCoverageXML;
    @SerializedName("InsPrcieXML")
    @Expose
    private String insPrcieXML;
    @SerializedName("InsPlanCode")
    @Expose
    private Integer insPlanCode;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getRqBaseID() {
        return rqBaseID;
    }

    public void setRqBaseID(String rqBaseID) {
        this.rqBaseID = rqBaseID;
    }

    public String getServiceStr() {
        return serviceStr;
    }

    public void setServiceStr(String serviceStr) {
        this.serviceStr = serviceStr;
    }

    public String getExc() {
        return exc;
    }

    public void setExc(String exc) {
        this.exc = exc;
    }

    public String getInsCoverageXML() {
        return insCoverageXML;
    }

    public void setInsCoverageXML(String insCoverageXML) {
        this.insCoverageXML = insCoverageXML;
    }

    public String getInsPrcieXML() {
        return insPrcieXML;
    }

    public void setInsPrcieXML(String insPrcieXML) {
        this.insPrcieXML = insPrcieXML;
    }

    public Integer getInsPlanCode() {
        return insPlanCode;
    }

    public void setInsPlanCode(Integer insPlanCode) {
        this.insPlanCode = insPlanCode;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
