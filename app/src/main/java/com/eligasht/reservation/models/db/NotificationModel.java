package com.eligasht.reservation.models.db;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;
/**
 * Created by Reza Nejati on 22,May,2018
 */
public class NotificationModel extends SugarRecord  {
    @Unique
    private Long id;

    String title;
    String body;
    Long date;

    public NotificationModel(String title, String body, Long date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }
    public NotificationModel() {

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
