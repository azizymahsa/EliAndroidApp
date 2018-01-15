package com.reserv.myapplicationeli.models.hotel.adapter;

/**
 * Created by Reza.nejati on 1/9/2018.
 */

public class FilterModel {
    boolean star1;
    boolean star2;
    boolean star3;
    boolean star4;
    boolean star5;
    boolean bestSeler;
    boolean bestOff;
    boolean remove;

    public FilterModel(boolean star1, boolean star2, boolean star3, boolean star4, boolean star5, boolean bestSeler, boolean bestOff, boolean remove) {
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
        this.bestSeler = bestSeler;
        this.bestOff = bestOff;
        this.remove = remove;
    }

    public boolean isStar1() {
        return star1;
    }

    public void setStar1(boolean star1) {
        this.star1 = star1;
    }

    public boolean isStar2() {
        return star2;
    }

    public void setStar2(boolean star2) {
        this.star2 = star2;
    }

    public boolean isStar3() {
        return star3;
    }

    public void setStar3(boolean star3) {
        this.star3 = star3;
    }

    public boolean isStar4() {
        return star4;
    }

    public void setStar4(boolean star4) {
        this.star4 = star4;
    }

    public boolean isStar5() {
        return star5;
    }

    public void setStar5(boolean star5) {
        this.star5 = star5;
    }

    public boolean isBestSeler() {
        return bestSeler;
    }

    public void setBestSeler(boolean bestSeler) {
        this.bestSeler = bestSeler;
    }

    public boolean isBestOff() {
        return bestOff;
    }

    public void setBestOff(boolean bestOff) {
        this.bestOff = bestOff;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
