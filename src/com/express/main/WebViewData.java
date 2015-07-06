package com.express.main;

import com.example.express.R;
import com.express.application.Myapplication;


import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewData extends Activity {

	ProgressDialog proDialog = null;
	String Url = "";
	Myapplication myapp;
	WebView web = null;
  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.webview);
		myapp=(Myapplication) getApplication();
		
		System.out.println("hahahwo");
		web = (WebView) this.findViewById(R.id.webView1);
		web.getSettings().setJavaScriptEnabled(true);
		// webView1.setWebViewClient(new WebViewClient());
		/*myapp = (Myapplication) getApplication();
		boolean netWork = myapp.detect(WebViewData.this);*/
		Url = getIntent().getStringExtra("Url");
		System.out.println("地址" + Url);
		/*
		 * if(!netWork){ Toast.makeText(WebViewData.this, "网络出错啦，请检查网络设置！",
		 * 2000).show(); }else{
		 * 
		 * webView1.loadUrl(Url);
		 * 
		 * if(proDialog!=null){ proDialog.dismiss();
		 * System.out.println("shabi");
		 * 
		 * } }
		 */
		if (web != null) {
			web.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageFinished(WebView view, String url) {
					proDialog.dismiss();
				}
			});
			if (myapp.getStatus()==0) {
				Toast.makeText(WebViewData.this, "网络出错啦，请检查网络设置！", 2000).show();
				proDialog.dismiss();
				loadUrl(Url);
				finish();
				
			} else {
				loadUrl(Url);
			}
		}

	}

	public void loadUrl(String url) {
		if (web != null) {

			// proDialog = ProgressDialog.show(this,null,"页面加载中，请稍后..");
			proDialog = ProgressDialog.show(WebViewData.this, "",
					"数据加载中..请稍后....", true, true);
		
			web.getSettings().setDomStorageEnabled(true);
			web.loadUrl(url);

			web.reload();
		}

	}

}
