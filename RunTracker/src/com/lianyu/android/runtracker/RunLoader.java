package com.lianyu.android.runtracker;

import android.content.Context;

public class RunLoader extends DataLoader<Run> {

	private long mRunId;

	public RunLoader(Context context, long runId) {
		super(context);
		// TODO Auto-generated constructor stub
		mRunId = runId;
	}

	@Override
	public Run loadInBackground() {
		// TODO Auto-generated method stub
		return RunManager.get(getContext()).getRun(mRunId);
	}

}
