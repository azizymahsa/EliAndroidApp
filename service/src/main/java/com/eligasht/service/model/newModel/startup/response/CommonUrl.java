
package com.eligasht.service.model.newModel.startup.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonUrl {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Url")
    @Expose
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
