package com.eligasht.reservation.views.components.stickyheaders;

import java.util.ArrayList;

/**
 * Created by hoseinraeisi on 1/11/18.
 */

public class Section<T> {

    private String title;
    private ArrayList<T> list = new ArrayList<T>();

    public Section() {
    }

    public Section(String title, ArrayList<T> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public final void setList(T value) {
        getList().add(value);
    }
}
