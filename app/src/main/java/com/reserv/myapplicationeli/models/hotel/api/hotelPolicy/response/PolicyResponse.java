package com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.response;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class PolicyResponse {
    public final ArrayList<GetHotelPolicyResult> GetHotelPolicyResult;

    public PolicyResponse(ArrayList<com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.response.GetHotelPolicyResult> getHotelPolicyResult) {
        GetHotelPolicyResult = getHotelPolicyResult;
    }
}
