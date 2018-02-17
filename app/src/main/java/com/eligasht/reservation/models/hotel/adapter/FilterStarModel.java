package com.eligasht.reservation.models.hotel.adapter;

/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 * Copyright © 2017
 */
public class FilterStarModel {
    String title;
    boolean check;
    int star;

    public FilterStarModel(String title, boolean check, int star) {
        this.title = title;
        this.check = check;
        this.star = star;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
