package com.reserv.myapplicationeli.api.hotel.getHotelDetail;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelAvailModelResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class GetHotelDetail extends BaseAPI {
    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

    }

    @Override
    protected void onBeforeExecute() {

    }

    @Override
    protected void onAfterExecute() {

    }
  /*  private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/GetHotelDetail";
    public HotelAvailModelResponse hotelAvailModelResponse;

    HotelDetailRequestModel hotelDetailRequestModel;

    public interface GetDetail {
        @RawRes
        @POST(GetHotelDetail.ACTION_NAME)
        Call<HotelAvailModelResponse> get_detail(
                @Body HotelDetailRequestModel hotelAvailRequestModel
        );
    }


    public GetHotelDetail(HotelAvailRequestModel hotelAvailRequestModel) {
        this.hotelAvailRequestModel = hotelAvailRequestModel;
        Log.e("hotel",hotelAvailRequestModel.toString());
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetDetail getDetail = retrofit.create(GetDetail.class);
        Call<HotelAvailModelResponse> call = get_detail.get_hotel(hotelAvailRequestModel);
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

    }*/
}


