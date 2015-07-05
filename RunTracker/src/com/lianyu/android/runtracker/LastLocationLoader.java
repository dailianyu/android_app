package com.lianyu.android.runtracker;

import android.content.Context;
import android.location.Location;

public class LastLocationLoader extends DataLoader<Location> {

	private long mRunId;

	public LastLocationLoader(Context context, long runId) {
		super(context);
		// TODO Auto-generated constructor stub
		mRunId = runId;
	}

	@Override
	public Location loadInBackground() {
		// TODO Auto-generated method stub
		return RunManager.get(getContext()).getLastLocationForRun(mRunId);
	}

}
