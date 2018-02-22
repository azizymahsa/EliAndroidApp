package com.eligasht.reservation.models.hotel.api.hotelPolicy.test;

import com.eligasht.reservation.models.hotel.api.Errors;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class GetHotelPolicyResult {

        private HCancellationPolicies[] HCancellationPolicies;

        private ArrayList<com.eligasht.reservation.models.hotel.api.Errors> Errors;

        public HCancellationPolicies[] getHCancellationPolicies ()
        {
            return HCancellationPolicies;
        }

    public void setHCancellationPolicies (HCancellationPolicies[] HCancellationPolicies)
    {
        this.HCancellationPolicies = HCancellationPolicies;
    }

    public ArrayList<com.eligasht.reservation.models.hotel.api.Errors> getErrors() {
        return Errors;
    }

    public void setErrors(ArrayList<com.eligasht.reservation.models.hotel.api.Errors> errors) {
        Errors = errors;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [HCancellationPolicies = ";
    }
}

