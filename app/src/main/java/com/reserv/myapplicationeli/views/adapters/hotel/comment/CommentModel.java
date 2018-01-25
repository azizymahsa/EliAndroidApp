package com.reserv.myapplicationeli.views.adapters.hotel.comment;

/**
 * Created by Reza.nejati on 1/25/2018.
 */

public class CommentModel {
    public int like;
    public int disLike;
    public String title;
    public String comment;
    public String date;
    public String name;

    public CommentModel(int like, int disLike, String title, String comment, String date, String name) {
        this.like = like;
        this.disLike = disLike;
        this.title = title;
        this.comment = comment;
        this.date = date;
        this.name = name;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDisLike() {
        return disLike;
    }

    public void setDisLike(int disLike) {
        this.disLike = disLike;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
