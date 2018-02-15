package com.eligasht.reservation.api.hotel.room;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.eligasht.reservation.models.hotel.api.rooms.response.GetRoomsListResponse;

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

