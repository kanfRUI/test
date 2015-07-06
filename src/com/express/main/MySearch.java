package com.express.main;

import java.util.List;

import com.example.express.R;
import com.express.adapter.MySearchAdapter;
import com.express.application.Myapplication;


import com.express.bean.Info;
import com.express.model.MySearchModel;
import com.express.model.SearchModel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MySearch extends Fragment {
	private MySearchAdapter adapter;
	private List<Info> list;

	Myapplication myapp;
	private String phone = "";
	private ListView lv;
	ProgressDialog proDialog = null;
	private static final String URL ="http://2.expressbox.sinaapp.com/index.php/Home/BoxStatus/phoneselect/?phone=";
	
	/**
     * 
     * 
     */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myview = inflater.inflate(R.layout.myserch_layout, container,
				false);

		lv = (ListView) myview.findViewById(R.id.listView2);
		 init();
		return myview;
	}

	// 判断是否有网络和是否已经登录
	private void init() {
		myapp = (Myapplication) getActivity().getApplication();
		//boolean netWork = myapp.detect(getActivity());*/
		phone = myapp.getName();
		//netWork=new NetWorkChange();
		System.out.println(phone);
		/*if (netWork.getStatus()==0) {
			Toast.makeText(getActivity(), "网络出错啦，请检查网络设置！", 2000).show();
		} else*/ 
		if (phone.equals("me")) {

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("温馨提示！");
			builder.setMessage("你当前的身份是游客，请登录！");

			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							//
							//getActivity().finish();
							dialog.cancel();
							Toast.makeText(getActivity(), "您取消了登录，请回到首页登录！否则无法查询到你的信息。", 1000).show();
						}
					});
			builder.setPositiveButton("登录",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent in = new Intent(getActivity(), Login.class);
							startActivity(in);
							getActivity().finish();
						}
					});

			builder.create().show();

		} else {
			proDialog = ProgressDialog.show(getActivity(), "",
					"数据加载中..请稍后....", true, true);
			init2();
		}

	}

	private void init2() {

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				System.out.println(phone);

				list = MySearchModel.getData(URL, phone);
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {

				initInfo();
				if(proDialog!=null){
					proDialog.dismiss();
				}
			}
		}.execute();
	}

	private void initInfo() {

		adapter = new MySearchAdapter(list, getActivity());
		lv.setAdapter(adapter);

	}

}
