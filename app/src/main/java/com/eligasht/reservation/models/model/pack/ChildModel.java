package com.eligasht.reservation.models.model.pack;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class ChildModel {

    private ChildAgeRange childAgeRange = ChildAgeRange.F0T2;
    private String title;
    boolean anim;

    public ChildModel(String title, boolean anim) {
        this.title = title;
        this.anim = anim;
    }

    public boolean isAnim() {
        return anim;
    }

    public void setAnim(boolean anim) {
        this.anim = anim;
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
