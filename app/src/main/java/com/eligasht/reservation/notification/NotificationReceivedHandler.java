package com.eligasht.reservation.notification;
import com.eligasht.reservation.models.NotificationEntity;
import com.eligasht.reservation.models.db.NotificationModel;
import com.eligasht.reservation.tools.Prefs;
import com.google.gson.Gson;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.greenrobot.eventbus.EventBus;
/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 * Copyright © 2017
 */
public class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    @Override
    public void notificationReceived(OSNotification notification) {
        Gson gson = new Gson();
        NotificationEntity notificationEntity = null;
        String json = notification.payload.rawPayload.replaceAll("/", "");
        Prefs.putInt("notifiCounter",Prefs.getInt("notifiCounter",0)+1);
        try {
            notificationEntity = gson.fromJson(json, NotificationEntity.class);
            NotificationModel notificationModel = new NotificationModel(notification.payload.title, notification.payload.body, notificationEntity.getGoogleSentTime());
            notificationModel.save();

            EventBus.getDefault().post(new NotificationModel(notification.payload.title, notification.payload.body, notificationEntity.getGoogleSentTime()));

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
