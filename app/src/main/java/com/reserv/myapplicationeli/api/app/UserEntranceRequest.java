package com.reserv.myapplicationeli.api.app;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.request.UserRequest;
import com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.response.UserEntranceResponse;
import com.reserv.myapplicationeli.models.hotel.getprefactor.call.RequestPrefactor;
import com.reserv.myapplicationeli.models.hotel.getprefactor.response.GetPrefactorResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 2/4/2018.
 */

public class UserEntranceRequest extends BaseAPI {
    public final static String ACTION_NAME = "Common/StaticDataService.svc/MobileAppStartupService";
    public UserEntranceResponse entranceResponse;
    UserRequest userRequest;

    public interface GetUser {
        @RawRes
        @POST(UserEntranceRequest.ACTION_NAME)
        Call<UserEntranceResponse> get_user(
                @Body UserRequest userRequest
        );
    }

    public UserEntranceRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetUser getUser = retrofit.create(GetUser.class);
        Call<UserEntranceResponse> call = getUser.get_user(userRequest);
        try {
            entranceResponse = call.execute().body();

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


