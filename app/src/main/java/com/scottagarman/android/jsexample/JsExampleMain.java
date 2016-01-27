package com.scottagarman.android.jsexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

public class JsExampleMain extends Activity {
	private WebView wv;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//create interface
		JsInterface jsInterface = new JsInterface();

		//get webview and enable js
		wv = (WebView) findViewById(R.id.web_view);
		wv.getSettings().setJavaScriptEnabled(true);

		//add interface
		wv.addJavascriptInterface(jsInterface, "android");//android is the keyword that will be exposed in js

		//load file
		wv.loadUrl("file:///android_asset/test.html");
	}

	public void click(View view){
		wv.evaluateJavascript("javascript:showMessage('Hello World!')", null);
	}

	//javascript interface
	private class JsInterface {
		//function that will be called from assets/test.js
		//js example: android.log('my message');
		public String data() {
			return "DATA FROM ANDROID";
		}

		public void log(String msg) {
			Log.d("MSG FROM JAVASCRIPT", msg);
		}
	}
}