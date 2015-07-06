package com.express.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.express.R;
import com.express.application.Myapplication;


public class Help extends Activity {
	//TextView tv_title;
	WebView webview=null;
	ProgressDialog proDialog = null;
	private static final String Url ="http://2.lgrr.sinaapp.com/help.html";
	Myapplication myapp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_layout);
		//tv_title=(TextView) this.findViewById(R.id.title_help);
		webview=(WebView)this.findViewById(R.id.web_help);
	
		myapp = (Myapplication) getApplication();
		
		
		
		if (webview != null) {
			webview.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageFinished(WebView view, String url) {
					proDialog.dismiss();
				}
			});
			if (myapp.getStatus()==0) {
				Toast.makeText(Help.this, "网络出错啦，请检查网络设置！", 2000).show();
				proDialog.dismiss();
				finish();
			} else {
				loadUrl(Url);
			}
		}

	}

	public void loadUrl(String url) {
		if (webview != null) {

			
			proDialog = ProgressDialog.show(Help.this, "",
					"数据加载中..请稍后....", true, true);
			webview.getSettings().setDomStorageEnabled(true);
			webview.loadUrl(url);

			webview.reload();
		}

	}
	
	

}
