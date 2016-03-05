package com.ss.staysafe;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;

public class VideoRec extends Activity implements SurfaceHolder.Callback {
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				wl.release();
			}
		}, 3000);
				
				
				
//		MainActivity.wl.release();
		super.onDestroy();
	}
	
    MediaRecorder recorder;
    SurfaceHolder holder;
    Chronometer chronometer;
	
    WakeLock wl;
	PowerManager pow;
	
    public String n;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
    	pow = (PowerManager)getSystemService(Context.POWER_SERVICE);
    	wl = pow.newWakeLock(PowerManager.FULL_WAKE_LOCK, "");
    	super.onCreate(savedInstanceState);
    	wl.acquire();
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        
	        recorder = new MediaRecorder();
	        initRecorder();
		setContentView(R.layout.activity_video_rec);
		chronometer = (Chronometer) findViewById(R.id.chronometer2);
    	
		SurfaceView cameraView = (SurfaceView) findViewById(R.id.CameraView1);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
        cameraView.setClickable(false);

}
        private void initRecorder() {
        	
        	File staySafe = new File(Environment.getExternalStorageDirectory(), "Stay Safe 365 Videos");

        	if(!staySafe.exists()) {                                 
        	  staySafe.mkdirs();
        	}
            recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);

            CamcorderProfile cpHigh = CamcorderProfile
                    .get(CamcorderProfile.QUALITY_HIGH);
            recorder.setProfile(cpHigh);
            Random r = new Random();
			int x;
			n = "365_";
			for (int i=1;i<=9;i++)
			{
				x = r.nextInt(9);
				n = n + x;
			}
            recorder.setOutputFile("/sdcard/Stay Safe 365 Videos/" + n + ".mp4");
            recorder.setMaxDuration(21000); 
            recorder.setMaxFileSize(25000000);
        }

        private void prepareRecorder() {
            recorder.setPreviewDisplay(holder.getSurface());

            try {
                recorder.prepare();
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        }

       public void surfaceCreated(SurfaceHolder holder) {
        	chronometer.start();
        	
            
        	try{
        		prepareRecorder();
	            recorder.start();
	            

	        	Timer t = new Timer();
	        	t.schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
		            VideoRec.this.finish();
					}
				}, 21000);
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        		
        	}
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                int height) {
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
               recorder.stop();

	            recorder.release();
	            Intent intent = new Intent(VideoRec.this,MainActivity.class);
				startActivity(intent);
                
            finish();
        }
    }