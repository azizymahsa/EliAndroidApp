package com.reserv.myapplicationeli.models.model;

import java.util.List;

/**
 * Created by Mahsa.azizi on 1/15/2018.
 */

public class AboutModel {

    private String MainDescription ;
    private List<SectionModel> Sections ;



    public String getMainDescription() {
        return MainDescription;
    }

    public void setMainDescription(String mainDescription) {
        MainDescription = mainDescription;
    }

    public List<SectionModel> getSections() {
        return Sections;
    }

    public void setSections(List<SectionModel> sections) {
        Sections = sections;
    }


}
