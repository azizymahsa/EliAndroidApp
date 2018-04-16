
package com.eligasht.service.model.hotel.transport.request;

import com.eligasht.service.model.identity.Identity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportReq {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("Param")
    @Expose
    private Param param;
    @SerializedName("TmpRq")
    @Expose
    private String tmpRq;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public String getTmpRq() {
        return tmpRq;
    }

    public void setTmpRq(String tmpRq) {
        this.tmpRq = tmpRq;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
