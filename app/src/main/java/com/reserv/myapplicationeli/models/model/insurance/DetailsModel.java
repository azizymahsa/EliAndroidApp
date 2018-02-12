package com.reserv.myapplicationeli.models.model.insurance;

/**
 * Created by Reza.nejati on 2/12/2018.
 */

public class DetailsModel {
    public String title;
    public String price;

    public DetailsModel(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
