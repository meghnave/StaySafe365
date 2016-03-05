package com.ss.staysafe;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
public class FakeCallMain extends Activity {
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(FakeCallMain.this,MainActivity.class);
		startActivity(intent);
		super.onBackPressed();
	}

	public static EditText name;
	public static String n;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fake_call_main);
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(null, 0);
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		Button b4 = (Button) findViewById(R.id.button4);
		Button b5 = (Button) findViewById(R.id.button5);
		Button b6 = (Button) findViewById(R.id.button6);
		
		name = (EditText) findViewById(R.id.editText1);
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try
				{
				if (!name.getText().toString().equals(""))
				{
					n = name.getText().toString();
					n = Character.toString(n.charAt(0)).toUpperCase() + n.substring(1);
					
				}
				
				else{
				Random r = new Random();
				int x;
				n = "+919";
				for (int i=1;i<=9;i++)
				{
					x = r.nextInt(9);
					n = n + x;
				}
				}
					
				FakeCallMain.this.finish();
				Intent intent = new Intent(FakeCallMain.this,CallScreen.class);
				startActivity(intent);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					
				}
			}

			
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FakeCallMain.this.finish();
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run()
					{
						try
						{
						if (!name.getText().toString().equals(""))
						{
							n = name.getText().toString();
							n = Character.toString(n.charAt(0)).toUpperCase() + n.substring(1);
							
						}
						
						else{
						Random r = new Random();
						int x;
						n = "+919";
						for (int i=1;i<=9;i++)
						{
							x = r.nextInt(9);
							n = n + x;
						}
						}
							
						Intent intent = new Intent(FakeCallMain.this,CallScreen.class);
						startActivity(intent);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							
						}
					}
				}, 5000);
			}
		});
		
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FakeCallMain.this.finish();
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run()
					{	try
					{
						if (!name.getText().toString().equals(""))
						{
							n = name.getText().toString();
							n = Character.toString(n.charAt(0)).toUpperCase() + n.substring(1);
							
						}
						
						else{
						Random r = new Random();
						int x;
						n = "+919";
						for (int i=1;i<=9;i++)
						{
							x = r.nextInt(9);
							n = n + x;
						}
						}

						Intent intent = new Intent(FakeCallMain.this,CallScreen.class);
						startActivity(intent);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							
						}
					}
				}, 10000);
			}
		});
		
		b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FakeCallMain.this.finish();
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run()
					{
						try
						{
						if (!name.getText().toString().equals(""))
						{
							n = name.getText().toString();
							n = Character.toString(n.charAt(0)).toUpperCase() + n.substring(1);
						}
						
						else{
						Random r = new Random();
						int x;
						n = "+919";
						for (int i=1;i<=9;i++)
						{
							x = r.nextInt(9);
							n = n + x;
						}
						}
						Intent intent = new Intent(FakeCallMain.this,CallScreen.class);
						startActivity(intent);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							
						}
					}
				}, 60000);
			}
		});
		
		b5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FakeCallMain.this.finish();
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run()
					{
						try
						{
						if (!name.getText().toString().equals(""))
						{
							n = name.getText().toString();
							n = Character.toString(n.charAt(0)).toUpperCase() + n.substring(1);
							
						}
						
						else{
						Random r = new Random();
						int x;
						n = "+919";
						for (int i=1;i<=9;i++)
						{
							x = r.nextInt(9);
							n = n + x;
						}
						}
						
						Intent intent = new Intent(FakeCallMain.this,CallScreen.class);
						startActivity(intent);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							
						}
					}
				}, 300000);
			}
		});
		
		b6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FakeCallMain.this.finish();
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run()
					{
						try
						{
						if (!name.getText().toString().equals(""))
						{
							n = name.getText().toString();
							n = Character.toString(n.charAt(0)).toUpperCase() + n.substring(1);
							
						}
						
						else{
						Random r = new Random();
						int x;
						n = "+919";
						for (int i=1;i<=9;i++)
						{
							x = r.nextInt(9);
							n = n + x;
						}
						}

						Intent intent = new Intent(FakeCallMain.this,CallScreen.class);
						startActivity(intent);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							
						}
					}
				}, 600000);
			}
		});
	}

	
}