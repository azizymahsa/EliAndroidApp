package com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.response;

import java.util.ArrayList;
import java.util.List;

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
