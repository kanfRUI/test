package com.express.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.express.main.MessageActivity;

public class MyPushMessageReceiver extends BroadcastReceiver{

	private static final String TAG = "便捷梦柜客户端";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {

		} else if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {

		} else if (intent.getAction().equals(	
			PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {
			Intent intent1=new Intent(context, MessageActivity.class);
			
			Log.i(TAG, "title = " + intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE));
			Log.i(TAG, "content = " + intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT));
			intent1.putExtra("title", intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE));
			intent1.putExtra("content", intent.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT));
			intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			context.startActivity(intent1);
			
		}
	}
}
