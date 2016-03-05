package com.ss.staysafe;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCont extends Activity implements OnClickListener{
	
	public String contact;
	public int c;
	EditText contact1;
	EditText contact2;
	EditText contact3;
	EditText contact4;
	EditText contact5;
	public static String filename1 = "cont1";
	public static String filename2 = "cont2";
	public static String filename3 = "cont3";
	public static String filename4 = "cont4";
	public static String filename5 = "cont5";
	public static SharedPreferences prefs1;
	public static SharedPreferences prefs2;
	public static SharedPreferences prefs3;
	public static SharedPreferences prefs4;
	public static SharedPreferences prefs5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_cont);setupVariables();
		prefs1 = getSharedPreferences(filename1, 0);
		prefs2 = getSharedPreferences(filename2, 0);
		prefs3 = getSharedPreferences(filename3, 0);
		prefs4 = getSharedPreferences(filename4, 0);
		prefs5 = getSharedPreferences(filename5, 0);

		String data1 = prefs1.getString("sharedString", "");
		contact1.setText(data1);
		String data2 = prefs2.getString("sharedString", "");
		contact2.setText(data2);
		String data3 = prefs3.getString("sharedString", "");
		contact3.setText(data3);
		String data4 = prefs4.getString("sharedString", "");
		contact4.setText(data4);
		String data5 = prefs5.getString("sharedString", "");
		contact5.setText(data5);
		
		Button b1 = (Button)findViewById(R.id.button1);
		   b1.setOnClickListener(new OnClickListener() {
		        public void onClick(View v) {
		        	c=1;
		            // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
		            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		            startActivityForResult(intent, 1);                
		        }
		    });
		   
		   Button b2 = (Button)findViewById(R.id.button2);
		   b2.setOnClickListener(new OnClickListener() {
		        public void onClick(View v) {
		        	c=2;
		            // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
		            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		            startActivityForResult(intent, 1);                
		        }
		    });
		   Button b3 = (Button)findViewById(R.id.button3);
		   b3.setOnClickListener(new OnClickListener() {
		        public void onClick(View v) {
		        	c=3;
		            // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
		            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		            startActivityForResult(intent, 1);                
		        }
		    });
		   Button b4 = (Button)findViewById(R.id.button4);
		   b4.setOnClickListener(new OnClickListener() {
		        public void onClick(View v) {
		        	c=4;
		            // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
		            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		            startActivityForResult(intent, 1);                
		        }
		    });

		   Button b5 = (Button)findViewById(R.id.button5);
		   b5.setOnClickListener(new OnClickListener() {
		        public void onClick(View v) {
		        	c=5;
		            // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
		            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		            startActivityForResult(intent, 1);                
		        }
		    });

		   Button save = (Button) findViewById(R.id.button6);
		   save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String stringData1 = contact1.getText().toString();
				SharedPreferences.Editor editor1 = prefs1.edit();
				editor1.putString("sharedString", stringData1);
				editor1.commit();
				
				MainActivity.s1 = prefs1.getString("sharedString", "0");
				Log.i("add_cont", MainActivity.s1);
				
				String stringData2 = contact2.getText().toString();
				SharedPreferences.Editor editor2 = prefs2.edit();
				editor2.putString("sharedString", stringData2);
				editor2.commit();
				
				MainActivity.s2 = prefs2.getString("sharedString", "0");
				Log.i("add_cont", MainActivity.s2);
				
				String stringData3 = contact3.getText().toString();
				SharedPreferences.Editor editor3 = prefs3.edit();
				editor3.putString("sharedString", stringData3);
				editor3.commit();
				
				MainActivity.s3 = prefs3.getString("sharedString", "0");
				Log.i("add_cont", MainActivity.s3);
				
				String stringData4 = contact4.getText().toString();
				SharedPreferences.Editor editor4 = prefs4.edit();
				editor4.putString("sharedString", stringData4);
				editor4.commit();
				
				MainActivity.s4 = prefs4.getString("sharedString", "0");
				Log.i("add_cont", MainActivity.s4);
				
				String stringData5 = contact5.getText().toString();
				SharedPreferences.Editor editor5 = prefs5.edit();
				editor5.putString("sharedString", stringData5);
				editor5.commit();
				
				MainActivity.s5 = prefs5.getString("sharedString", "0");
				Log.i("add_cont", MainActivity.s5);
				
				Toast.makeText(getApplicationContext(), "Contacts Saved!", Toast.LENGTH_LONG).show();
				AddCont.this.finish();
			}
		});
		   
		 //clear code - start
		   
		   Button b7 = (Button) findViewById(R.id.button7);
		   Button b8 = (Button) findViewById(R.id.button8);
		   Button b9 = (Button) findViewById(R.id.button9);
		   Button b10 = (Button) findViewById(R.id.button10);
		   Button b11 = (Button) findViewById(R.id.button11);
		   
		   b7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				contact1.setText("");
				MainActivity.s1 = "";
			}
		});
		   
		   b8.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					contact2.setText("");
					MainActivity.s2 = "";
				}
			});
		   
		   b9.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					contact3.setText("");
					MainActivity.s3 = "";
				}
			});
		   
		   b10.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					contact4.setText("");
					MainActivity.s4 = "";
				}
			});
		   
		   b11.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					contact5.setText("");
					MainActivity.s5 = "";
				}
			});
		   
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data != null) {
	        Uri uri = data.getData();

	        if (uri != null) {
	            Cursor c = null;
	            try {
	                c = getContentResolver().query(uri, new String[]{ 
	                            ContactsContract.CommonDataKinds.Phone.NUMBER,  
	                            ContactsContract.CommonDataKinds.Phone.TYPE 
	                            },
	                        null, null, null);

	                if (c != null && c.moveToFirst()) {
	                    String number = c.getString(0);
	                   int type = c.getInt(1);
	                    showSelectedNumber(number);
	                   
	                }
	            } finally { 
	                if (c != null) {
	                    c.close();
	                }
	            }
	        }
	    }
	}

	public void showSelectedNumber(String number) {
	    
		 contact=number.toString();
		 int x;
		 if (c==1)
		 {
		 String c1=contact1.toString();
		 }
		 else if (c==2)
		 {
		 String c1=contact2.toString();
		 }
		 else if (c==3)
		 {
		 String c1=contact3.toString();
		 }
		 else if (c==4)
		 {
		 String c1=contact4.toString();
		 }
		 x=contact.length(); 
		 if (x>=10)
		 {
			 if(contact.startsWith("+"))
			 {
			 contact=contact.substring(3);
			 }
			 contact=contact.replace(" ", "");
			 if(c==1)
				 contact1.setText(contact);
			 else if(c==2)
				 contact2.setText(contact);
			 else if(c==3)
				 contact3.setText(contact);
			 else if(c==4)
				 contact4.setText(contact);
			 else if(c==5)
				 contact5.setText(contact);
				 
			 Toast.makeText(this, contact, Toast.LENGTH_LONG).show();}}
		 
	private void setupVariables() {
		// TODO Auto-generated method stub
		Button save = (Button) findViewById(R.id.button6);
		contact1 = (EditText) findViewById(R.id.editText1);
		contact2 = (EditText) findViewById(R.id.editText2);
		contact3 = (EditText) findViewById(R.id.editText3);
		contact4 = (EditText) findViewById(R.id.editText4);
		contact5 = (EditText) findViewById(R.id.editText5);
		save.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	
}