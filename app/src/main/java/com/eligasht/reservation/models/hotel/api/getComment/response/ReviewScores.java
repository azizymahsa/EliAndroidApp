package com.eligasht.reservation.models.hotel.api.getComment.response;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class ReviewScores {
    private String SumScore;

    private String HotelID;

    private String ReviewID;

    private String ScoreAmount;

    private String ParamTitle;

    private String ScoreParameterID;

    private String ScoreCounts;

    public String getSumScore ()
    {
        return SumScore;
    }

    public void setSumScore (String SumScore)
    {
        this.SumScore = SumScore;
    }

    public String getHotelID ()
    {
        return HotelID;
    }

    public void setHotelID (String HotelID)
    {
        this.HotelID = HotelID;
    }

    public String getReviewID ()
    {
        return ReviewID;
    }

    public void setReviewID (String ReviewID)
    {
        this.ReviewID = ReviewID;
    }

    public String getScoreAmount ()
    {
        return ScoreAmount;
    }

    public void setScoreAmount (String ScoreAmount)
    {
        this.ScoreAmount = ScoreAmount;
    }

    public String getParamTitle ()
    {
        return ParamTitle;
    }

    public void setParamTitle (String ParamTitle)
    {
        this.ParamTitle = ParamTitle;
    }

    public String getScoreParameterID ()
    {
        return ScoreParameterID;
    }

    public void setScoreParameterID (String ScoreParameterID)
    {
        this.ScoreParameterID = ScoreParameterID;
    }

    public String getScoreCounts ()
    {
        return ScoreCounts;
    }

    public void setScoreCounts (String ScoreCounts)
    {
        this.ScoreCounts = ScoreCounts;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SumScore = "+SumScore+", HotelID = "+HotelID+", ReviewID = "+ReviewID+", ScoreAmount = "+ScoreAmount+", ParamTitle = "+ParamTitle+", ScoreParameterID = "+ScoreParameterID+", ScoreCounts = "+ScoreCounts+"]";
    }
}

