package com.eligasht.reservation.models.hotel.api.getComment.response;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class Reviews{
    public final String SubmitEmail;

    public final  String Device;

    public final  ReviewScores[] ReviewScores;

    public final  String ReviewID;

    public final  String IsRecommended;

    public final  String CommentsCount;

    public final  String WebUserID;

    public final  String TravelSeason;

    public final  String TraveSeasonID;

    public final  String Title;

    public final  String SubmitNickName;

    public final  String AverageScore;

    public final  String SubmitDate;

    public final  String TravelTypeID;

    public final  String SubmitName;

    public final  String SubmitLocation;

    public final  String PriceType;


    public final  String TravelType;

    public final  String AgcUserID;

    public final  String HelpfulAmount;

    public final  String PriceTypeID;

    public final  String SubmitImgURL;

    public final  String Content;

    public Reviews(String submitEmail, String device, com.eligasht.reservation.models.hotel.api.getComment.response.ReviewScores[] reviewScores, String reviewID, String isRecommended, String commentsCount, String webUserID, String travelSeason, String traveSeasonID, String title, String submitNickName, String averageScore, String submitDate, String travelTypeID, String submitName, String submitLocation, String priceType, String travelType, String agcUserID, String helpfulAmount, String priceTypeID, String submitImgURL, String content) {
        SubmitEmail = submitEmail;
        Device = device;
        ReviewScores = reviewScores;
        ReviewID = reviewID;
        IsRecommended = isRecommended;
        CommentsCount = commentsCount;
        WebUserID = webUserID;
        TravelSeason = travelSeason;
        TraveSeasonID = traveSeasonID;
        Title = title;
        SubmitNickName = submitNickName;
        AverageScore = averageScore;
        SubmitDate = submitDate;
        TravelTypeID = travelTypeID;
        SubmitName = submitName;
        SubmitLocation = submitLocation;
        PriceType = priceType;
        TravelType = travelType;
        AgcUserID = agcUserID;
        HelpfulAmount = helpfulAmount;
        PriceTypeID = priceTypeID;
        SubmitImgURL = submitImgURL;
        Content = content;
    }
}