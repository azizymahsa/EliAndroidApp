package com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.test;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class GetHotelPolicyResult {

        private HCancellationPolicies[] HCancellationPolicies;

        private String Error;

        public HCancellationPolicies[] getHCancellationPolicies ()
        {
            return HCancellationPolicies;
        }

    public void setHCancellationPolicies (HCancellationPolicies[] HCancellationPolicies)
    {
        this.HCancellationPolicies = HCancellationPolicies;
    }

    public String getError ()
    {
        return Error;
    }

    public void setError (String Error)
    {
        this.Error = Error;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [HCancellationPolicies = "+HCancellationPolicies+", Error = "+Error+"]";
    }
}

