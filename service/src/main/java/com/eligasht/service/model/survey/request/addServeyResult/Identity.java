
package com.eligasht.service.model.survey.request.addServeyResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identity {

    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("TermianlId")
    @Expose
    private String termianlId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTermianlId() {
        return termianlId;
    }

    public void setTermianlId(String termianlId) {
        this.termianlId = termianlId;
    }

}
