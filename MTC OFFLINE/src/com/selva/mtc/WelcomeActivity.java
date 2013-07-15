package com.selva.mtc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends Activity {
	/** Called when the activity is first created. */

	Handler handler;
	Thread thread;
	long SPLASH_SCREEN_TIME_IN_MILLIS = 1000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		handler = new Handler();
	}

	@Override
	protected void onResume() {
		super.onResume();
		thread = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(SPLASH_SCREEN_TIME_IN_MILLIS);
					handler.post(new Runnable() {
						public void run() {
							goToNextScreen();
						}
					});
				} catch (InterruptedException e) {
				}
			}
		};

		thread.start();
	}

	protected void goToNextScreen() {
		Intent intent = new Intent(this, DashboardActivity.class);
		startActivity(intent);
		finish();
	}
}