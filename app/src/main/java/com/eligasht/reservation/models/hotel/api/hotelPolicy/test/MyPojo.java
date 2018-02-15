package com.eligasht.reservation.models.hotel.api.hotelPolicy.test;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class MyPojo {

        private GetHotelPolicyResult GetHotelPolicyResult;

    public GetHotelPolicyResult getGetHotelPolicyResult ()
    {
        return GetHotelPolicyResult;
    }

    public void setGetHotelPolicyResult (GetHotelPolicyResult GetHotelPolicyResult)
    {
        this.GetHotelPolicyResult = GetHotelPolicyResult;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [GetHotelPolicyResult = "+GetHotelPolicyResult+"]";
    }
}

