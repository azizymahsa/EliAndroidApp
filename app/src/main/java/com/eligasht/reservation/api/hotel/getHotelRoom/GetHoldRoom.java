package com.eligasht.reservation.api.hotel.getHotelRoom;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.holdSelectedRoom.call.HoldSelectedRoomRequest;
import com.eligasht.reservation.models.hotel.api.holdSelectedRoom.response.HoldSelectRoomResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class GetHoldRoom extends BaseAPI {

    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/HoldSelectedRoom";
    public HoldSelectRoomResponse holdSelectRoomResponse;

    HoldSelectedRoomRequest holdSelectedRoomRequest;

    public interface GetDetail {
        @RawRes
        @POST(GetHoldRoom.ACTION_NAME)
        Call<HoldSelectRoomResponse> get_detail(
                @Body HoldSelectedRoomRequest holdSelectedRoomRequest
        );
    }

    public GetHoldRoom(HoldSelectedRoomRequest holdSelectedRoomRequest) {
        this.holdSelectedRoomRequest = holdSelectedRoomRequest;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetDetail getDetail = retrofit.create(GetDetail.class);
        Call<HoldSelectRoomResponse> call = getDetail.get_detail(holdSelectedRoomRequest);
        try {
            holdSelectRoomResponse = call.execute().body();

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


