package com.eligasht.reservation.api.hotel.hotelAvail;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.eligasht.reservation.models.hotel.api.hotelAvail.response.HotelAvailModelResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class HotelAvailApi extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/HotelAvail";
    public HotelAvailModelResponse hotelAvailModelResponse;

    HotelAvailRequestModel hotelAvailRequestModel;

    public interface GetHotel {
        @RawRes
        @POST(HotelAvailApi.ACTION_NAME)
        Call<HotelAvailModelResponse> get_hotel(
                @Body HotelAvailRequestModel hotelAvailRequestModel
        );
    }


    public HotelAvailApi(HotelAvailRequestModel hotelAvailRequestModel) {
        this.hotelAvailRequestModel = hotelAvailRequestModel;
        Log.e("hotel",hotelAvailRequestModel.toString());
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetHotel getHotel = retrofit.create(GetHotel.class);
        Call<HotelAvailModelResponse> call = getHotel.get_hotel(hotelAvailRequestModel);
        try {
            hotelAvailModelResponse = call.execute().body();

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

