package com.reserv.myapplicationeli.api.app;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.api.hotel.comment.GetComment;
import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.hotel.api.getComment.call.GetCommentRequest;
import com.reserv.myapplicationeli.models.hotel.api.getComment.response.GetCommentResponse;
import com.reserv.myapplicationeli.models.hotel.getprefactor.call.RequestPre;
import com.reserv.myapplicationeli.models.hotel.getprefactor.call.RequestPrefactor;
import com.reserv.myapplicationeli.models.hotel.getprefactor.response.GetPrefactorResponse;

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
        @POST("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetPreFactorDetails")
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


