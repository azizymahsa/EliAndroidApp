package com.eligasht.service.model.newModel.auth.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAuth {
    @SerializedName("grant_type")
    @Expose
    private String grant_type;
    @SerializedName("username")
    @Expose

    private String username;
    @SerializedName("password")
    @Expose
    private String password;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}