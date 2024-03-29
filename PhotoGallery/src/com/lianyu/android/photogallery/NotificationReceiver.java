package com.lianyu.android.photogallery;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {
	
	private static final String TAG = "NotificationReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "received result: " + getResultCode());
		if (getResultCode() != Activity.RESULT_OK)
			return;
		
		int requestCode = intent.getIntExtra("REQUEST_CODE", 0);
		Notification notification = intent.getParcelableExtra("NOTIFICATION");
		
		NotificationManager notificationmanager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationmanager.notify(requestCode, notification);
	}

}
