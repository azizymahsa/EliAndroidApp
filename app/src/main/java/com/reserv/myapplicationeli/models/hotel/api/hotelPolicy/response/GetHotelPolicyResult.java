package com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class GetHotelPolicyResult {
 public final HCancellationPolicies [] hCancellationPolicies;

    public GetHotelPolicyResult(HCancellationPolicies [] hCancellationPolicies) {
        this.hCancellationPolicies = hCancellationPolicies;
    }
}
