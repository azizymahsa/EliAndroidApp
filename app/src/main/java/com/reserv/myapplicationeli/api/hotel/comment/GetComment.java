package com.reserv.myapplicationeli.api.hotel.comment;

import android.support.annotation.RawRes;
import android.util.Log;

import com.reserv.myapplicationeli.base.BaseAPI;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequsetAddComment;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.response.AddCommentsResult;
import com.reserv.myapplicationeli.models.hotel.api.getComment.call.GetCommentRequest;
import com.reserv.myapplicationeli.models.hotel.api.getComment.response.GetCommentResponse;
import com.reserv.myapplicationeli.models.hotel.api.getComment.response.GetHotelReviewResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class GetComment extends BaseAPI { private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/GetHotelReview";
    public GetCommentResponse getHotelReviewResult;
    GetCommentRequest getCommentRequest;

    public interface GetR {
        @RawRes
        @POST(GetComment.ACTION_NAME)
        Call<GetCommentResponse> get_comment(
                @Body GetCommentRequest getCommentRequest
        );
    }

    public GetComment(GetCommentRequest getCommentRequest) {
        this.getCommentRequest = getCommentRequest;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        GetR getR = retrofit.create(GetR.class);
        Call<GetCommentResponse> call = getR.get_comment(getCommentRequest);
        try {
            getHotelReviewResult = call.execute().body();

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


