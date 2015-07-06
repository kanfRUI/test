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

	// �ж��Ƿ���������Ƿ��Ѿ���¼
	private void init() {
		myapp = (Myapplication) getActivity().getApplication();
		//boolean netWork = myapp.detect(getActivity());*/
		phone = myapp.getName();
		//netWork=new NetWorkChange();
		System.out.println(phone);
		/*if (netWork.getStatus()==0) {
			Toast.makeText(getActivity(), "����������������������ã�", 2000).show();
		} else*/ 
		if (phone.equals("me")) {

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("��ܰ��ʾ��");
			builder.setMessage("�㵱ǰ��������οͣ����¼��");

			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							//
							//getActivity().finish();
							dialog.cancel();
							Toast.makeText(getActivity(), "��ȡ���˵�¼����ص���ҳ��¼�������޷���ѯ�������Ϣ��", 1000).show();
						}
					});
			builder.setPositiveButton("��¼",
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
					"���ݼ�����..���Ժ�....", true, true);
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
