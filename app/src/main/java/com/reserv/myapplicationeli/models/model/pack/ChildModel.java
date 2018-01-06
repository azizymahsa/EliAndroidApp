package com.reserv.myapplicationeli.models.model.pack;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class ChildModel {

    private ChildAgeRange childAgeRange = ChildAgeRange.F0T2;
    private String title;

    public ChildModel(String title) {
        this.title = title;
    }

    public ChildModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChildAgeRange getChildAgeRange() {
        return childAgeRange;
    }

    public void setChildAgeRange(ChildAgeRange childAgeRange) {
        this.childAgeRange = childAgeRange;
    }

}
