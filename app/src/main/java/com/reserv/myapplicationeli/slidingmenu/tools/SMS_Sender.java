package com.reserv.myapplicationeli.slidingmenu.tools;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public abstract class SMS_Sender {
	Context context;

	public SMS_Sender(Context context) {
		this.context = context;
	}

	private BroadcastReceiver receiver1 = null, receiver2 = null;

	public void sendMessage(final String message, short port,
			String phoneNumber, boolean isDataMessage) {
		
		TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		   String mPhoneNumbera = tMgr.getLine1Number();
		   ////
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";
		final PendingIntent sendIntent = PendingIntent.getBroadcast(context, 0,new Intent(SENT), 0);
		final PendingIntent delivery = PendingIntent.getBroadcast(context, 0,new Intent(DELIVERED), 0);
		context.registerReceiver(receiver1 = new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					messageSendingSuccessfull();
					Log.e("SENT_SMS", "RESULT_OK");
					break;
				default:
					messageSendingFailed();
					Log.e("SENT_SMS", "FAIL");
					break;
				}
			}
		}, new IntentFilter(SENT));

		context.registerReceiver(receiver2 = new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					messageDeliverySuccessfull();
					Log.e("DELIVERY_SMS", "RESULT_OK");
					break;
				case Activity.RESULT_CANCELED:
					messageDeliveryFailed();
					Log.e("DELIVERY_SMS", "RESULT_CANCELED");
					break;
				}
			}
		}, new IntentFilter(DELIVERED));
		byte[] messageData = null;
		try {
			messageData = message.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		final SmsManager smsManager = SmsManager.getDefault();
		if (isDataMessage)
			smsManager.sendDataMessage(phoneNumber, "", port, messageData,sendIntent, delivery);
		else
			smsManager.sendTextMessage(phoneNumber, "", message, sendIntent,delivery);
	}//end send message

	public void unregisterReceivers() {
		context.unregisterReceiver(receiver1);
		context.unregisterReceiver(receiver2);
	}

	public abstract void messageSendingSuccessfull();

	public abstract void messageSendingFailed();

	public abstract void messageDeliverySuccessfull();

	public abstract void messageDeliveryFailed();

}
