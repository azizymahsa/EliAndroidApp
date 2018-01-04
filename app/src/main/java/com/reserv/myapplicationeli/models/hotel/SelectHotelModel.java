package com.reserv.myapplicationeli.models.hotel;

/**
 * Created by Reza.nejati on 1/4/2018.
 */

public class SelectHotelModel {
    private String name;
  /*  private String city;
    private String title;
    private String board;
    private String price;
    private String imageUrl;
*/

    public SelectHotelModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}