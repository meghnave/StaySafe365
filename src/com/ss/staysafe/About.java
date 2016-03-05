package com.ss.staysafe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		

		String text = "<html><body style=\"text-align:justify\"> %s </body></Html>";
		String data = "This application is made because there needs to be an application which can help you to stay safe and in touch with family and friends if such a situation occurs. With the help of this application, you can alert a circle of people that you are in some kind of trouble.<br/><br/><br/>Stay Safe 365<br/> Version 1.0.1<br/><br/><br/><br/> Developed by -<br/> Meghna Verma<br/> Siddhant Nagpal<br/><br/>Contact us: staysafe365@gmail.com";

		WebView webView = (WebView) findViewById(R.id.webView2);
		webView.loadData(String.format(text, data), "text/html", "utf-8");

	}
}
