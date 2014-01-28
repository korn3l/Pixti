package com.CornelCocioaba.Pixti.Android;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.CornelCocioaba.Pixti.Engine.Engine;
import com.CornelCocioaba.Pixti.Engine.R;
import com.CornelCocioaba.Pixti.Engine.R.menu;

public class GameActivity extends Activity {

	protected Engine mEngine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mEngine = new Engine(this);
		
		if (supportsGLES20()) {
			setContentView(mEngine.getSurfaceView());
		} else {
			Toast.makeText(this, "This device does not support OpenGL ES 2.0", Toast.LENGTH_LONG).show();
			return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_open_gl, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		mEngine.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mEngine.onResume();
	}

	private boolean supportsGLES20() {
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo info = activityManager.getDeviceConfigurationInfo();

		final Boolean supportsEs2 = info.reqGlEsVersion >= 0x20000
				|| (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && (Build.FINGERPRINT
						.startsWith("generic")
						|| Build.FINGERPRINT.startsWith("unknown")
						|| Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL
							.contains("Android SDK built for x86")));

		return supportsEs2;
	}
}