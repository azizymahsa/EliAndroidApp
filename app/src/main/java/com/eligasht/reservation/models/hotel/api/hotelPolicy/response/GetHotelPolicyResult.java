package com.eligasht.reservation.models.hotel.api.hotelPolicy.response;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class GetHotelPolicyResult {
 public final HCancellationPolicies [] hCancellationPolicies;

    public GetHotelPolicyResult(HCancellationPolicies [] hCancellationPolicies) {
        this.hCancellationPolicies = hCancellationPolicies;
    }
}
