package com.eligasht.reservation.models.hotel.api.getComment.response;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class ReviewFilterItems {
    private String ReviewCategoryID;

    private String ReviewsCount;

    private String ReviewCategoryTitle;

    private String ReviewCategoryItemsTitle;

    private String ReviewCategoryItemsID;

    public String getReviewCategoryID ()
    {
        return ReviewCategoryID;
    }

    public void setReviewCategoryID (String ReviewCategoryID)
    {
        this.ReviewCategoryID = ReviewCategoryID;
    }

    public String getReviewsCount ()
    {
        return ReviewsCount;
    }

    public void setReviewsCount (String ReviewsCount)
    {
        this.ReviewsCount = ReviewsCount;
    }

    public String getReviewCategoryTitle ()
    {
        return ReviewCategoryTitle;
    }

    public void setReviewCategoryTitle (String ReviewCategoryTitle)
    {
        this.ReviewCategoryTitle = ReviewCategoryTitle;
    }

    public String getReviewCategoryItemsTitle ()
    {
        return ReviewCategoryItemsTitle;
    }

    public void setReviewCategoryItemsTitle (String ReviewCategoryItemsTitle)
    {
        this.ReviewCategoryItemsTitle = ReviewCategoryItemsTitle;
    }

    public String getReviewCategoryItemsID ()
    {
        return ReviewCategoryItemsID;
    }

    public void setReviewCategoryItemsID (String ReviewCategoryItemsID)
    {
        this.ReviewCategoryItemsID = ReviewCategoryItemsID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ReviewCategoryID = "+ReviewCategoryID+", ReviewsCount = "+ReviewsCount+", ReviewCategoryTitle = "+ReviewCategoryTitle+", ReviewCategoryItemsTitle = "+ReviewCategoryItemsTitle+", ReviewCategoryItemsID = "+ReviewCategoryItemsID+"]";
    }
}

