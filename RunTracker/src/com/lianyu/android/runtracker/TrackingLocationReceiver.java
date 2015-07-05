package com.lianyu.android.runtracker;

import android.content.Context;
import android.location.Location;

public class TrackingLocationReceiver extends LocationReceiver {

	@Override
	protected void onLocationReceived(Context context, Location loc) {
		// TODO Auto-generated method stub
		RunManager.get(context).insertLocation(loc);
	}

}
