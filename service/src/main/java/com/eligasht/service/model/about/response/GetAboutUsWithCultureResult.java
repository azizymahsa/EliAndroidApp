
package com.eligasht.service.model.about.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAboutUsWithCultureResult {

    @SerializedName("MainDescription")
    @Expose
    private String mainDescription;
    @SerializedName("Sections")
    @Expose
    private List<Section> sections = null;

    public String getMainDescription() {
        return mainDescription;
    }

    public void setMainDescription(String mainDescription) {
        this.mainDescription = mainDescription;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
