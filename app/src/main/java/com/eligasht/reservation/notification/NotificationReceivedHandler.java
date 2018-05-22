package com.eligasht.reservation.notification;

import android.util.Log;

import com.eligasht.reservation.models.db.NotificationModel;
import com.eligasht.reservation.models.eventbus.RoomsModelBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        String json =  notification.payload.rawPayload.replaceAll("/","");


        try{
             notificationEntity =gson.fromJson(json, NotificationEntity.class);


        }catch (Exception e){
        }




        NotificationModel notificationModel = new NotificationModel( notification.payload.title ,  notification.payload.body , notificationEntity.getGoogleSentTime());
        notificationModel.save();
        EventBus.getDefault().post( new NotificationModel( notification.payload.title ,  notification.payload.body , notificationEntity.getGoogleSentTime()));

    /*    if (data != null) {
            try {
               // Log.e("tesi2", "notificationReceived: ", );
            } catch (Exception e) {
                e.printStackTrace();
            }*/


        }

    public static String toPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }








}
