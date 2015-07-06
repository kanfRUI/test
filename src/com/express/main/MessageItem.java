package com.express.main;

import com.example.express.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageItem extends Activity {
	TextView tv_title,tv_content;
   Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_item);
		tv_title=(TextView) this.findViewById(R.id.tv_title_m);
		tv_content=(TextView) this.findViewById(R.id.tv_content_m);
		intent=getIntent();
		tv_content.setText(intent.getStringExtra("content"));
		tv_title.setText(intent.getStringExtra("title"));
		
		
	}
	

}
