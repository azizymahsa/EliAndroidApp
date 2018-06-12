package com.eligasht.reservation.api.hotel;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.detail.GetHotelDetailResponse;
import com.eligasht.reservation.models.hotel.api.detail.call.GetHotelDetailRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class GetHotelDetail extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/GetHotelDetail";
    public GetHotelDetailResponse getHotelDetailResult;

    GetHotelDetailRequest getHotelDetailRequest;

    public interface Get_D {
        @RawRes
        @POST(GetHotelDetail.ACTION_NAME)
        Call<GetHotelDetailResponse> get_detail(@Body GetHotelDetailRequest holdSelectRoomResponse
        );
    }

    public GetHotelDetail(GetHotelDetailRequest getHotelDetailRequest) {
        this.getHotelDetailRequest = getHotelDetailRequest;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        Get_D getD = retrofit.create(Get_D.class);
        Call<GetHotelDetailResponse> call = getD.get_detail(getHotelDetailRequest);
        try {
            getHotelDetailResult = call.execute().body();
        } catch (Exception e) {
            Log.e("errorrrrrr",e.getMessage());
        }
    }

    @Override
    protected void onBeforeExecute() {

    }

    @Override
    protected void onAfterExecute() {

    }
}


