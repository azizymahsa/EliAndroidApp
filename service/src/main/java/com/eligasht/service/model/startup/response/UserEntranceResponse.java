
package com.eligasht.service.model.startup.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserEntranceResponse {

    @SerializedName("CanEnter")
    @Expose
    private Boolean canEnter;
    @SerializedName("MinAppVersion")
    @Expose
    private String minAppVersion;
    @SerializedName("SearchNotes")
    @Expose
    private List<SearchNote> searchNotes = null;

    public Boolean getCanEnter() {
        return canEnter;
    }

    public void setCanEnter(Boolean canEnter) {
        this.canEnter = canEnter;
    }

    public String getMinAppVersion() {
        return minAppVersion;
    }

    public void setMinAppVersion(String minAppVersion) {
        this.minAppVersion = minAppVersion;
    }

    public List<SearchNote> getSearchNotes() {
        return searchNotes;
    }

    public void setSearchNotes(List<SearchNote> searchNotes) {
        this.searchNotes = searchNotes;
    }

}
