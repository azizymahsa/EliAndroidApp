package com.eligasht.reservation.views.activities.new_survey.model;

public class InfoRowdata {
    public boolean isclicked=false;
    public int index;
    public int id;
    public String text;


    public InfoRowdata(boolean isclicked,int index,int id,String text)
    {
        this.index=index;
        this.isclicked=isclicked;
        this.id=id;
        this.text=text;

    }
}
