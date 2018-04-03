
package com.eligasht.service.model.flight.response.DomesticFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("TermianlId")
    @Expose
    private String termianlId;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("ToCode")
    @Expose
    private String toCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTermianlId() {
        return termianlId;
    }

    public void setTermianlId(String termianlId) {
        this.termianlId = termianlId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

}
