
package com.eligasht.service.model.newModel.flight.prefactor.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetPreFactor {

    @SerializedName("BookingCode")
    @Expose
    private String bookingCode;
    @SerializedName("Followers")
    @Expose
    private List<Follower> followers = null;
    @SerializedName("FollowerId")
    @Expose
    private Integer followerId;
    @SerializedName("FactorDetails")
    @Expose
    private FactorDetails factorDetails;
    @SerializedName("DiscountDetail")
    @Expose
    private DiscountDetail discountDetail;

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public FactorDetails getFactorDetails() {
        return factorDetails;
    }

    public void setFactorDetails(FactorDetails factorDetails) {
        this.factorDetails = factorDetails;
    }

    public DiscountDetail getDiscountDetail() {
        return discountDetail;
    }

    public void setDiscountDetail(DiscountDetail discountDetail) {
        this.discountDetail = discountDetail;
    }

}
