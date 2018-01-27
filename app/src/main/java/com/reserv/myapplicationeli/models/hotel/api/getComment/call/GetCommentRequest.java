package com.reserv.myapplicationeli.models.hotel.api.getComment.call;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class GetCommentRequest {
    private Request request;

    public Request getRequest ()
    {
        return request;
    }

    public void setRequest (Request request)
    {
        this.request = request;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [request = "+request+"]";
    }
}

