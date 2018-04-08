
package com.eligasht.service.model.hotel.hotelAvail.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Identity {

    @SerializedName("Password")
    @Expose
    private String password="123qwe!@#QWE";
    @SerializedName("TermianlId")
    @Expose
    private String termianlId="Mobile";
    @SerializedName("UserName")
    @Expose
    private String userName="EligashtMlb";

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void showLog() {
        android.util.Log.e(this.getClass().getSimpleName(), toString());
    }

    public String toString() {
        return new ToStringBuilder(this).append("password", password).append("termianlId", termianlId).append("userName", userName).toString();
    }

}