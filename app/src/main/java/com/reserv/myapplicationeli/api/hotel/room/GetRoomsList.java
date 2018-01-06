package com.reserv.myapplicationeli.api.hotel.room;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelAvailModelResponse;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.response.GetRoomsListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class GetRoomsList extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/GetRoomsList";
    public GetRoomsListResponse getRoomsListResponse;

    GetRoomsHotelRequest getRoomsHotelRequest;

    public interface GetRooms {
        @RawRes
        @POST(GetRoomsList.ACTION_NAME)
        Call<GetRoomsListResponse> get_rooms(
                @Body GetRoomsHotelRequest getRoomsHotelRequest
        );
    }


    public GetRoomsList(GetRoomsHotelRequest getRoomsHotelRequest) {
        this.getRoomsHotelRequest = getRoomsHotelRequest;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetRooms getRooms = retrofit.create(GetRooms.class);
        Call<GetRoomsListResponse> call = getRooms.get_rooms(getRoomsHotelRequest);
        try {
            getRoomsListResponse = call.execute().body();

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

