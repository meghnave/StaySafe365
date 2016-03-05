package com.ss.staysafe;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class InCall extends Activity {
	
	TextView name;
	private Chronometer chronometer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_in_call);

		try
		{
		chronometer = (Chronometer) findViewById(R.id.chronometer1);
		
		chronometer.start();
		
		name = (TextView) findViewById(R.id.textView1);
		
		name.setText(FakeCallMain.n);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Button dec2 = (Button) findViewById(R.id.decline2);
		dec2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
		
		dec2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chronometer.stop();
				CallScreen.kl.reenableKeyguard();
				InCall.this.finish();
		
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		chronometer.stop();
		InCall.this.finish();
		CallScreen.kl.reenableKeyguard();
		super.onBackPressed();
	}

}