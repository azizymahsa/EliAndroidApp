package com.eligasht.reservation.views.activities.new_survey.model;

public class InfoRowdata {
    public boolean isclicked=false;
    public int index;
    public int id;
    public String text;



    public InfoRowdata(boolean isclicked, int index, int id, String text)
    {
        this.index=index;
        this.isclicked=isclicked;
        this.id=id;
        this.text=text;

    }
    public boolean isIsclicked() {
        return isclicked;
    }

    public void setIsclicked(boolean isclicked) {
        this.isclicked = isclicked;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
