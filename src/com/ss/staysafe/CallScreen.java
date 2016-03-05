package com.ss.staysafe;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CallScreen extends Activity {

	TextView name;
	public static Ringtone r;
	Vibrator vib;
	public static KeyguardManager.KeyguardLock kl;
	public static KeyguardManager km;
	WakeLock wl;
	PowerManager pow;

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		wl.release();
		super.onDestroy();
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		kl = km.newKeyguardLock("MyKeyguardLock");
		kl.disableKeyguard();		

		pow = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wl = pow.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP
		        | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
		wl.acquire();
		Intent settingIntent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		startActivityForResult(settingIntent, 0);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_call_screen);
		
		
		
		name = (TextView) findViewById(R.id.textView1);
		
		name.setText(FakeCallMain.n);
		
		Button butt1 = (Button) findViewById(R.id.butt1);
		Button butt2 = (Button) findViewById(R.id.butt2);
		
		butt1.getBackground().setColorFilter(Color.rgb(121, 178, 32), PorterDuff.Mode.MULTIPLY);
		butt2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
		
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				
			
				long[] pattern = {500, 500, 500, 500,  500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
							500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 
							500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500};
				vib.vibrate(pattern, -1);
				Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
				r = RingtoneManager.getRingtone(getApplicationContext(), notification);
				vib.vibrate(pattern, -1);
				r.play();
			}
		}, 50);
		
		
		butt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				r.stop();
				vib.cancel();
				CallScreen.this.finish();
				
				
				Intent intent = new Intent(CallScreen.this,InCall.class);
				startActivity(intent);
				
			}
		});
		
		
		butt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				r.stop();
				vib.cancel();
				kl.reenableKeyguard();
				CallScreen.this.finish();
		
			}
		});
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		r.stop();
//		vib.cancel();
//		CallScreen.this.finish();
//		kl.reenableKeyguard();
	}

}