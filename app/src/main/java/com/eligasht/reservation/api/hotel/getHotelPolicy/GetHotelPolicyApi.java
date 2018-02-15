package com.eligasht.reservation.api.hotel.getHotelPolicy;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.hotelPolicy.request.PolicyRequest;
import com.eligasht.reservation.models.hotel.api.hotelPolicy.test.MyPojo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class GetHotelPolicyApi extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/GetHotelPolicy";
    public MyPojo getHotelPolicyResponse;

    PolicyRequest policyRequest;

    public interface GetPolicy {
        @RawRes
        @POST(GetHotelPolicyApi.ACTION_NAME)
        Call<MyPojo> get_policy(
                @Body PolicyRequest policyRequest
        );
    }

    public GetHotelPolicyApi(PolicyRequest policyRequest) {
        this.policyRequest = policyRequest;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetPolicy getPolicy = retrofit.create(GetPolicy.class);
        Call<MyPojo> call = getPolicy.get_policy(policyRequest);
        try {
            getHotelPolicyResponse = call.execute().body();

        } catch (Exception e) {
            Log.e("errorrrrrr",e.getMessage() );

        }


    }

    @Override
    protected void onBeforeExecute() {

    }

    @Override
    protected void onAfterExecute() {

    }
}


