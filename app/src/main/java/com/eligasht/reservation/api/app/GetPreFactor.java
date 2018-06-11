package com.eligasht.reservation.api.app;

import android.support.annotation.RawRes;
import android.util.Log;
import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.getprefactor.call.RequestPrefactor;
import com.eligasht.reservation.models.hotel.getprefactor.response.GetPrefactorResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class GetPreFactor extends BaseAPI {
    public final static String ACTION_NAME = "Common/StaticDataService.svc/GetPreFactorDetails";
    public GetPrefactorResponse getPrefactorResponse;
    RequestPrefactor requestPre;
    public interface GetPre {
        @RawRes
        @POST(GetPreFactor.ACTION_NAME)
        Call<GetPrefactorResponse> get_pre(
                @Body RequestPrefactor requestPre
        );
    }

    public GetPreFactor(RequestPrefactor requestPre) {
        this.requestPre = requestPre;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetPre getPre = retrofit.create(GetPre.class);
        Call<GetPrefactorResponse> call = getPre.get_pre(requestPre);
        try {
            getPrefactorResponse = call.execute().body();
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


