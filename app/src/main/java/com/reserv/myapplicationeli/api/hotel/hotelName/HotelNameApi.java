package com.reserv.myapplicationeli.api.hotel.hotelName;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.api.hotel.hotelFlight.HotelFlightSearch;
import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.HotelAR;
import com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest.HotelFlightModelResponse;
import com.reserv.myapplicationeli.models.hotel.api.hotelName.request.HotelNameRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelName.response.GetHotelAjaxResult;
import com.reserv.myapplicationeli.models.hotel.api.hotelName.response.HotelNameApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class HotelNameApi extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "HotelFlight/HotelFlightService.svc/GetHotelAjax";
    public HotelNameApiResponse hotelAjaxResult;

    HotelNameRequest hotelAvailRequestModel;

    public interface GetHotelName {
        @RawRes
        @POST(HotelNameApi.ACTION_NAME)
        Call<HotelNameApiResponse> get_hotel(
                @Body HotelNameRequest hotelAvailRequestModel
        );
    }


    public HotelNameApi(HotelNameRequest hotelAvailRequestModel) {
        this.hotelAvailRequestModel = hotelAvailRequestModel;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetHotelName getHotelName = retrofit.create(GetHotelName.class);
        Call<HotelNameApiResponse> call = getHotelName.get_hotel(hotelAvailRequestModel);
        try {
            hotelAjaxResult = call.execute().body();

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

