package com.eligasht.reservation.api.hotel.comment;

import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.reservation.base.BaseAPI;
import com.eligasht.reservation.models.hotel.api.addcomment.call.RequsetAddComment;
import com.eligasht.reservation.models.hotel.api.addcomment.response.AddCommentsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class AddComment extends BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();
    public final static String ACTION_NAME = "Hotel/HotelService.svc/AddHotelReview";
    public AddCommentsResult addCommentsResult;
    RequsetAddComment requsetAddComment;

    public interface Add {
        @RawRes
        @POST(AddComment.ACTION_NAME)
        Call<AddCommentsResult> add_comment(
                @Body RequsetAddComment requsetAddComment
        );
    }

    public AddComment(RequsetAddComment requsetAddComment) {
        this.requsetAddComment = requsetAddComment;
        send();
    }

    @Override
    protected void onBuildUri() {

    }

    @Override
    protected void execute() {

        Add add = retrofit.create(Add.class);
        Call<AddCommentsResult> call = add.add_comment(requsetAddComment);
        try {
            addCommentsResult = call.execute().body();

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


