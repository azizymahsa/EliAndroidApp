package com.eligasht.reservation.models.hotel.api.addcomment.call;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class ReviewComment {
    public final int AgcUserID;
    public final String Content;
    public final int ReviewCommentID;
    public final int ReviewID;
    public final String SubmitEmail;
    public final String SubmitName;
    public final String Title;
    public final int WebUserID;

    public ReviewComment(int agcUserID, String content, int reviewCommentID, int reviewID, String submitEmail, String submitName, String title, int webUserID) {
        AgcUserID = agcUserID;
        Content = content;
        ReviewCommentID = reviewCommentID;
        ReviewID = reviewID;
        SubmitEmail = submitEmail;
        SubmitName = submitName;
        Title = title;
        WebUserID = webUserID;
    }
}
