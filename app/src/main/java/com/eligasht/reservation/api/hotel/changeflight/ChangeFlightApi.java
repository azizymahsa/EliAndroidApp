package com.eligasht.reservation.api.hotel.changeflight;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.api.hotel.comment.AddComment;
import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.addcomment.call.RequsetAddComment;
import com.eligasht.reservation.models.hotel.api.addcomment.response.AddCommentsResult;
import com.eligasht.reservation.models.hotel.api.changeflight.request.ChangeFlightApiRequest;
import com.eligasht.reservation.models.hotel.api.changeflight.response.ChangeFlightApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 2/21/2018.
 */

public class ChangeFlightApi extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "HotelFlight/HotelFlightService.svc/HotelPlusFlightChangeFlt";
    public ChangeFlightApiResponse changeFlightApiResponse;
    ChangeFlightApiRequest changeFlightApiRequest;

    public interface Change {
        @RawRes
        @POST(ChangeFlightApi.ACTION_NAME)
        Call<ChangeFlightApiResponse> change_f(
                @Body ChangeFlightApiRequest changeFlightApiRequest
        );
    }

    public ChangeFlightApi(ChangeFlightApiRequest changeFlightApiRequest) {
        this.changeFlightApiRequest = changeFlightApiRequest;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        Change add = retrofit.create(Change.class);
        Call<ChangeFlightApiResponse> call = add.change_f(changeFlightApiRequest);
        try {
            changeFlightApiResponse = call.execute().body();

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


