package com.reserv.myapplicationeli.api.hotel;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.request.AirportTransportServicePriceRequest;
import com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.respone.AirportTransportRespone;
import com.reserv.myapplicationeli.models.hotel.api.flightHotelRequest.HotelFlightModelResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class AirportTransportServicePrice extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "HotelFlight/HotelFlightService.svc/AirportTransportServicePrice";
    public AirportTransportRespone airportTransportRespone;

    AirportTransportServicePriceRequest hotelAvailRequestModel;

    public interface GetHotel {
        @RawRes
        @POST(AirportTransportServicePrice.ACTION_NAME)
        Call<AirportTransportRespone> get_hotel(
                @Body AirportTransportServicePriceRequest hotelAvailRequestModel
        );
    }


    public AirportTransportServicePrice(AirportTransportServicePriceRequest hotelAvailRequestModel) {
        this.hotelAvailRequestModel = hotelAvailRequestModel;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

       GetHotel getHotel = retrofit.create(GetHotel.class);
        Call<AirportTransportRespone> call = getHotel.get_hotel(hotelAvailRequestModel);
        try {
            airportTransportRespone = call.execute().body();

        } catch (Exception e) {
            Log.e("errorrrrrr", e.getMessage());

        }


    }

    @Override
    protected void onBeforeExecute() {

    }

    @Override
    protected void onAfterExecute() {

    }
}

