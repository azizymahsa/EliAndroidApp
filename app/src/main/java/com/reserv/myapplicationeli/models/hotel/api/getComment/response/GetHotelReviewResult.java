package com.reserv.myapplicationeli.models.hotel.api.getComment.response;

import com.reserv.myapplicationeli.models.hotel.api.Errors;

import java.util.List;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class GetHotelReviewResult {



    public final List<com.reserv.myapplicationeli.models.hotel.api.Errors> Errors ;

    public final HotelReview HotelReview;

    public GetHotelReviewResult(List<com.reserv.myapplicationeli.models.hotel.api.Errors> errors, com.reserv.myapplicationeli.models.hotel.api.getComment.response.HotelReview hotelReview) {
        Errors = errors;
        HotelReview = hotelReview;
    }
}

