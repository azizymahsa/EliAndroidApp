package com.reserv.myapplicationeli.models.hotel.api.hotelName.response;

import com.reserv.myapplicationeli.models.hotel.api.Errors;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class GetHotelAjaxResult {
  public final ArrayList<Errors>  errors;
  public final ArrayList<Hotels>  Hotels;

    public GetHotelAjaxResult(ArrayList<Errors> errors, ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelName.response.Hotels> hotels) {
        this.errors = errors;
        Hotels = hotels;
    }
}
