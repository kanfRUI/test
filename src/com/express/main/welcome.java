package com.express.main;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

import com.example.express.R;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


public class welcome extends Activity {
	private static final int GOTO_MAIN_ACTIVITY = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		Myhander mHandler=new Myhander();
		mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 3000);
		
	}
	
	private class Myhander extends Handler{

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			  switch (msg.what) {
              case GOTO_MAIN_ACTIVITY:
                  Intent intent = new Intent();
                  intent.setClass(welcome.this, MainActivity.class);
                  System.out.println("aaa");
                  startActivity(intent);
                  finish();
                  break;

              default:
                  break;
          }
			
		}
		
		
		
		
	


	
	
}
}
