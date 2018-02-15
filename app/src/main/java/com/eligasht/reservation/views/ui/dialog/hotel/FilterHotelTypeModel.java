package com.eligasht.reservation.views.ui.dialog.hotel;

/**
 * Created by Reza.nejati on 1/13/2018.
 */

public class FilterHotelTypeModel {
    String title;
    boolean check;

    public FilterHotelTypeModel(String title, boolean check) {
        this.title = title;
        this.check = check;
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
}
