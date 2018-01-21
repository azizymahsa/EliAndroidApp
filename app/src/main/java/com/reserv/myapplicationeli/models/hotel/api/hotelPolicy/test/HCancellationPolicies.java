package com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.test;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class HCancellationPolicies {
    private HCancellationPolicy[] HCancellationPolicy;

    private String Key;

    public HCancellationPolicy[] getHCancellationPolicy ()
    {
        return HCancellationPolicy;
    }

    public void setHCancellationPolicy (HCancellationPolicy[] HCancellationPolicy)
    {
        this.HCancellationPolicy = HCancellationPolicy;
    }

    public String getKey ()
    {
        return Key;
    }

    public void setKey (String Key)
    {
        this.Key = Key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [HCancellationPolicy = "+HCancellationPolicy+", Key = "+Key+"]";
    }
}
