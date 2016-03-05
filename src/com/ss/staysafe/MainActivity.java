package com.ss.staysafe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		bv.setText(Html.fromHtml("<big>Settings</big><br/><small><small><small><font color = #848482>Manage features 'to-be-on'.</font></small></small></small>"));
		b2.setText(Html.fromHtml("<big>Contacts</big><br/><small><small><small><font color = #848482>Add or change favourite people.</font></small></small></small>"));
		bf.setText(Html.fromHtml("<big>Fake Call</big><br/><small><small><small><font color = #848482>Fool someone with a dummy call.</font></small></small></small>"));
		b7.setText(Html.fromHtml("<big>Help</big><br/><small><small><small><font color = #848482>Well, you know what this is!</font></small></small></small>"));
		b8.setText(Html.fromHtml("<big>About</big><br/><small><small><small><font color = #848482>To know more!</font></small></small></small>"));
		b1.setText(Html.fromHtml("<big>START</big><br/><small><small><small><small><font color = #848482>Initiate emergency features!</font></small></small></small></small>"));
		super.onStart();
	}


	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(z==1)
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, 0);

		wl.release();
		
//		if(mp.isPlaying())
//		{
//			mp.stop();
//		}
//		
		super.onDestroy();
	}
	
	public static String s1 = " ",s2 = " ",s3 = " ",s4 = " ",s5 = " ";
	
	public static WakeLock wl;
	PowerManager pow;
	
	public EditText msg_txt;
	AudioManager mAudioManager;
	GpsTracker gps;
	public boolean msg_pref;
	public boolean alarm_pref;
	public boolean flash_pref;
	public boolean video_pref;

	int z;
	int p;
 	public Button b1;
 	
	public boolean isLightOn = false;
 	public Camera camera;
 	public MediaPlayer mp;
 	public Button bv;
 	public Button b2;
 	public Button bf;
 	public Button b7;
 	public Button b8;
 	
 	int originalVolume;
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		pow = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wl = pow.newWakeLock(PowerManager.FULL_WAKE_LOCK, "");
		super.onCreate(savedInstanceState);
		wl.acquire();
		setContentView(R.layout.activity_main);
		mp = MediaPlayer.create(MainActivity.this, R.raw.all);
		
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		
		if (!mWifi.isConnected()) {
		    		new AlertDialog.Builder(this)
	    .setTitle("Mobile Data")
	    .setMessage("Stay Safe 365 uses mobile data to get your current location. Allow it to use your mobile data? Data charges may apply.")
    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) { 
        	setMobileDataEnabled();
    		
        }
     })
    .setNegativeButton("No", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) { 
        	dialog.cancel();
        }
     })
     .show();
		}
		
		//Settings - start
		bv = (Button) findViewById(R.id.settings);
		bv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent_f=new Intent(MainActivity.this,Settings.class);
				startActivityForResult(intent_f, 0);
				
			}
		});
		//Settings - end
		
		b2=(Button) findViewById(R.id.edit_contacts);
		b2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent intent = new Intent(MainActivity.this,AddCont.class);
				startActivity(intent);
				
				}
		});
			
		//AddCont-end
		
		//fake_call - start
		
				bf = (Button) findViewById(R.id.fake);
				bf.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
						MainActivity.this.finish();
//						camera.release();
						Intent intent = new Intent(MainActivity.this,FakeCallMain.class);
						startActivity(intent);
						
					}
				});
				
		//fake_call - end
				
		//help-start
		b7=(Button) findViewById(R.id.help);
		
		b7.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent intent = new Intent(MainActivity.this,Help.class);
				startActivity(intent);
				}
		});
		//help-end

		//about-start
		b8=(Button) findViewById(R.id.about);
		b8.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent intent = new Intent(MainActivity.this,About.class);
				startActivity(intent);
				
			}
		});
		//about-end
		
		//video-start
		checkPref_v();
		//video-end
		
		//alarm-start
		checkPref_a();
		//alarm-end
		
		//flash-start
		checkPref_f();
		//flash-end
	
		//set_msg-start
		msg_txt = (EditText) findViewById(R.id.editMsg);
		
		checkPref_m();
		pref_msg();
		//set_msg-end
		
		//START button - start
		b1 = (Button) findViewById(R.id.start);
		
		
		b1.setOnClickListener(new OnClickListener() {									//START CODE
				
			@Override
			public void onClick(View arg0) {
				
				try {		

					if(msg_pref)
					{
						send_msg();
					}
					
					camera = Camera.open();
					
					final Parameters p = camera.getParameters();
					
				if(alarm_pref && !mp.isPlaying()) 
				{
					mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
					originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
				    mAudioManager.setSpeakerphoneOn(true);
		
				    mAudioManager.setMode(AudioManager.MODE_IN_CALL);
				    
				    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
					mp.start();
					
					Timer t = new Timer();
					t.schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							mp.stop();
							mAudioManager.setMode(AudioManager.MODE_NORMAL);
				
						}
					}, 45000);
					
					z=1;
				}
				
				if(flash_pref)
				{
					
					while(mp.isPlaying())
						{
						if (isLightOn) {
							for(int y=0;y<7;y++){
								p.setFlashMode(Parameters.FLASH_MODE_OFF);
								camera.setParameters(p);
								camera.stopPreview();
								isLightOn = false;
								}
						}
						
						else {
						   for(int y=0;y<7;y++){
							   p.setFlashMode(Parameters.FLASH_MODE_TORCH);
							   camera.setParameters(p);
							   camera.startPreview();
							   isLightOn = true;
						   }
						}
					}
					
					if (isLightOn) {
						
							p.setFlashMode(Parameters.FLASH_MODE_OFF);
							camera.setParameters(p);
							camera.stopPreview();
							isLightOn = false;
							}
					
					if(!alarm_pref)
					{
						final MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.mute);
						player.start();
						

						Timer t = new Timer();
						t.schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								player.stop();
								
								}
						}, 45000);
						
						while(player.isPlaying())
						{
						if (isLightOn) {
							for(int y=0;y<7;y++){
								p.setFlashMode(Parameters.FLASH_MODE_OFF);
								camera.setParameters(p);
								camera.stopPreview();
								isLightOn = false;
								}
						}
						
						else {
						   for(int y=0;y<7;y++){
							   p.setFlashMode(Parameters.FLASH_MODE_TORCH);
							   camera.setParameters(p);
							   camera.startPreview();
							   isLightOn = true;
						   }
						}
					}
					
					if (isLightOn) {
						
							p.setFlashMode(Parameters.FLASH_MODE_OFF);
							camera.setParameters(p);
							camera.stopPreview();
							isLightOn = false;
							}					
					}
					
					}

				camera.release();
				
				
				if(video_pref && flash_pref)
				{	
					
					{
						
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							
						@Override
						public void run() {
							// TODO Auto-generated method stub
							MainActivity.this.finish();
							Intent intent = new Intent(MainActivity.this,VideoRec.class);
							startActivity(intent);		
						}
				}, 1500);}
				
				}
				
				else if(video_pref && alarm_pref)
				{

					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainActivity.this.finish();
						Intent intent = new Intent(MainActivity.this,VideoRec.class);
						startActivity(intent);		
					}
				}, 46500);
				}
				
				else if(video_pref)
				{
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainActivity.this.finish();
						Intent intent = new Intent(MainActivity.this,VideoRec.class);
						startActivity(intent);		
					}
				}, 500);		
				}
				
			}
					catch (Exception e) {
						e.printStackTrace();
						
					}
				}
			});
			//START button - end
		
		}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		wl.release();
		super.onBackPressed();
	}

	private void setMobileDataEnabled() {
		ConnectivityManager dataManager;
		dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		Method dataMtd = null;
		try {
			dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataMtd.setAccessible(true);
		try {
			dataMtd.invoke(dataManager, true);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}

	public void checkPref_f() {
		SharedPreferences myPref2  = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		flash_pref = myPref2.getBoolean("flash_c", true);
		}

	public void checkPref_a() {
		SharedPreferences myPref1  = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		alarm_pref =   myPref1.getBoolean("alarm_c", true);
	}

	public void checkPref_m() {
		SharedPreferences myPref3  = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		msg_pref =   myPref3.getBoolean("msg_c", true);
	}
	
	public void checkPref_v() {
		SharedPreferences myPref4  = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		video_pref =   myPref4.getBoolean("vid_c", true);
	}

		public void pref_msg() {
		SharedPreferences msg_pref  = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String edit_msg=msg_pref.getString("msg","");
		msg_txt.setText(edit_msg);
	}

	public void send_msg() {
		try{
				
			AddCont.prefs1 = getSharedPreferences(AddCont.filename1, 0);
			AddCont.prefs2 = getSharedPreferences(AddCont.filename2, 0);
			AddCont.prefs3 = getSharedPreferences(AddCont.filename3, 0);
			AddCont.prefs4 = getSharedPreferences(AddCont.filename4, 0);
			AddCont.prefs5 = getSharedPreferences(AddCont.filename5, 0);
			
			s1 = AddCont.prefs1.getString("sharedString", " ");
			
			s2 = AddCont.prefs2.getString("sharedString", " ");
			
			s3 = AddCont.prefs3.getString("sharedString", " ");
				
			s4 = AddCont.prefs4.getString("sharedString", " ");
				
			s5 = AddCont.prefs5.getString("sharedString", " ");
			
			gps = new GpsTracker(MainActivity.this);
			String sms;
			// check if GPS enabled		
	        if(gps.canGetLocation()){
	        	
	        	double latitude = gps.getLatitude();
	        	double longitude = gps.getLongitude();
	        	
	        	sms = msg_txt.getText().toString() + "\nFollow my location: http://maps.google.com/?q=" + latitude + "," + longitude + "\n(approximate)";
	    		
	        }
	        else{
	        	sms = msg_txt.getText().toString() + "\nLocation Unavailable! Please Call Up||";
	        	
	        }
		
	        SmsManager smsManager = SmsManager.getDefault();	
		if(!s1.isEmpty()){
			smsManager.sendTextMessage(s1, null, sms, null, null);
		}
		if(!s2.isEmpty()){
			smsManager.sendTextMessage(s2, null, sms, null, null);
		}
		if(!s3.isEmpty()){
			smsManager.sendTextMessage(s3, null, sms, null, null);
		}
		if(!s4.isEmpty()){
			smsManager.sendTextMessage(s4, null, sms, null, null);
		}
		if(!s5.isEmpty()){
			smsManager.sendTextMessage(s5, null, sms, null, null);
		}
		
		if(!s1.isEmpty() || !s2.isEmpty() || !s3.isEmpty() || !s4.isEmpty() || !s5.isEmpty())
		{
		Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
		}
		}
		catch (Exception e1) {
			//Toast.makeText(getApplicationContext(), "error in msg", Toast.LENGTH_LONG).show();
				e1.printStackTrace();
		 }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);

		checkPref_f();
		checkPref_a();
		checkPref_m();
		checkPref_v();
		pref_msg();
		
	}}
