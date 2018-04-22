
package com.eligasht.service.model.about.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAbout {

    @SerializedName("GetAboutUsWithCultureResult")
    @Expose
    public GetAboutUsWithCultureResult getAboutUsWithCultureResult;

    public GetAboutUsWithCultureResult getGetAboutUsWithCultureResult() {
        return getAboutUsWithCultureResult;
    }

    public void setGetAboutUsWithCultureResult(GetAboutUsWithCultureResult getAboutUsWithCultureResult) {
        this.getAboutUsWithCultureResult = getAboutUsWithCultureResult;
    }

}
