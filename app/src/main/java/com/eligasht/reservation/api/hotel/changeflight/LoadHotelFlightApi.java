package com.eligasht.reservation.api.hotel.changeflight;
import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.changeflight.request.ChangeFlightApiRequest;
import com.eligasht.reservation.models.hotel.api.changeflight.response.ChangeFlightApiResponse;
import com.eligasht.reservation.models.hotel.api.flightchange.LoadHotelFlightApiRespone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 * Created by Reza.nejati on 2/21/2018.
 */
public class LoadHotelFlightApi extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "HotelFlight/HotelFlightService.svc/LoadFlight";
    public LoadHotelFlightApiRespone changeFlightApiResponse;
    ChangeFlightApiRequest changeFlightApiRequest;
    public interface Change {
        @RawRes
        @POST(LoadHotelFlightApi.ACTION_NAME)
        Call<LoadHotelFlightApiRespone> change_f(
                @Body ChangeFlightApiRequest changeFlightApiRequest
        );
    }

    public LoadHotelFlightApi(ChangeFlightApiRequest changeFlightApiRequest) {
        this.changeFlightApiRequest = changeFlightApiRequest;
        send();
    }


    @Override
    protected void onBuildUri() {
    }

    @Override
    protected void execute() {
        Change add = retrofit.create(Change.class);
        Call<LoadHotelFlightApiRespone> call = add.change_f(changeFlightApiRequest);
        try {
            changeFlightApiResponse = call.execute().body();
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


