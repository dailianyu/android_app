package com.lianyu.android.remotecontrol;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.view.Window;

public class RemoteControlActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new RemoteControlFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState, persistentState);
	}

}
