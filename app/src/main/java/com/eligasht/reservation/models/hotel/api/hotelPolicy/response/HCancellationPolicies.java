package com.eligasht.reservation.models.hotel.api.hotelPolicy.response;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class HCancellationPolicies {
    public final HCancellationPolicy [] HCancellationPolicy;
    public final String Key;

    public HCancellationPolicies(HCancellationPolicy[] HCancellationPolicy, String key) {
        this.HCancellationPolicy = HCancellationPolicy;
        Key = key;
    }
}
