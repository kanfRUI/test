package com.express.main;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.express.R;
import com.express.adapter.MessageAdapter;
import com.express.push.Message;
import com.express.push.MessageData;

public class MessageActivity extends Activity {
	private ListView mListView;
	private List<Message> lm;
	private MessageAdapter madapter;
	private ProgressDialog proDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_layout);
		proDialog = ProgressDialog.show(MessageActivity.this, "",
				"数据拼命加载中...请稍后...", true, true);
		mListView = (ListView) findViewById(R.id.list_old);
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
		init();
		System.out.println("1====1");
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Message message = lm.get(arg2);
				Intent intent = new Intent(MessageActivity.this,
						WebViewData.class);
				System.out.print("==========");
				// intent.putExtra("Mess_content",message.getContent());
				intent.putExtra("Url", message.getHref());

				startActivity(intent);

			}

		});

	}

	private void init() {
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				System.out.println("1111111111111");
				lm = MessageData.getMessageData();
			
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				System.out.println("11112");
				initMessage();
				
				if (proDialog != null) {
					proDialog.dismiss();
				}
			}

		}.execute();
	}

	private void initMessage() {
		System.out.println("11eeee");
		madapter = new MessageAdapter(lm, MessageActivity.this);
		mListView.setAdapter(madapter);

	}

}