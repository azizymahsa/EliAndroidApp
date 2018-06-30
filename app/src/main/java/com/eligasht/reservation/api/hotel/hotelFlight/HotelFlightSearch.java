package com.eligasht.reservation.api.hotel.hotelFlight;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.HotelAR;
import com.eligasht.reservation.models.hotel.api.flightHotelRequest.HotelFlightModelResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/15/2018.
 */

public class HotelFlightSearch extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "HotelFlight/HotelFlightService.svc/HotelFlightSearch";
    public HotelFlightModelResponse hotelFlightModelResponse;

    HotelAR hotelAvailRequestModel;

    public interface GetHotel {
        @RawRes
        @POST(HotelFlightSearch.ACTION_NAME)
        Call<HotelFlightModelResponse> get_hotel(
                @Body HotelAR hotelAvailRequestModel
        );
    }

    public HotelFlightSearch(HotelAR hotelAvailRequestModel) {
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
        Call<HotelFlightModelResponse> call = getHotel.get_hotel(hotelAvailRequestModel);
        try {
            hotelFlightModelResponse = call.execute().body();

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

