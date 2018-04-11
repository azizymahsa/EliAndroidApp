
package com.eligasht.service.model.startup.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchNote {

    @SerializedName("Notes")
    @Expose
    private List<String> notes = null;
    @SerializedName("Section")
    @Expose
    private String section;

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
