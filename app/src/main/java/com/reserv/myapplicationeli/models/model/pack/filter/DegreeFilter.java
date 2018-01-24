package com.reserv.myapplicationeli.models.model.pack.filter;

/**
 * Created byelham.bonyani on 1/20/18.
 */

public class DegreeFilter {

    public int star;
    public boolean isSelected;

    public DegreeFilter(int star) {
        this.star = star;
    }

    public DegreeFilter() {
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
