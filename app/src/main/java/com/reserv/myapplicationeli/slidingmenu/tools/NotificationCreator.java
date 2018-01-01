package com.reserv.myapplicationeli.slidingmenu.tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

public class NotificationCreator {
	public static void CreateNotification(Context context, Class className,
			Bundle extras, int drawable, String message, String title) {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent1 = new Intent(context, className);
		if (extras != null)
			intent1.putExtras(extras);
		intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent1.putExtra("message", message);
		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
				context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(drawable);
		builder.setContentTitle(title);
		builder.setContentText(message);
		builder.setContentIntent(pendingNotificationIntent);
		Uri alarmSound = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		builder.setSound(alarmSound);
		builder.setLights(Color.BLUE, 500, 500);
		long[] pattern = { 500, 500, 500, 500, 500, 500, 500, 500, 500 };
		builder.setVibrate(pattern);
		builder.setAutoCancel(true);
		manager.notify(1, builder.build());
	}
}
