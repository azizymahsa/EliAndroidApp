package com.reserv.myapplicationeli.api.hotel.getHotelPolicy;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.request.PolicyRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.response.GetHotelPolicyResponse;
import com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.test.MyPojo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class GetHotelPolicyApi extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "http://mobilews.eligasht.com/LightServices/Rest/Hotel/HotelService.svc/GetHotelPolicy";
    public MyPojo getHotelPolicyResponse;

    PolicyRequest policyRequest;

    public interface GetPolicy {
        @RawRes
        @POST("http://mobilews.eligasht.com/LightServices/Rest/Hotel/HotelService.svc/GetHotelPolicy")
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

