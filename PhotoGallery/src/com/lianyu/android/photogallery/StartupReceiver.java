package com.lianyu.android.photogallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class StartupReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean isOn = pref.getBoolean(PollService.PREF_IS_ALARM_ON, false);
		PollService.setServiceAlarm(context, isOn);
	}

}
