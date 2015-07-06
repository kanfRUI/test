package com.express.main;



import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.express.R;
import com.express.adapter.SearchAdapter;
import com.express.application.Myapplication;
import com.express.bean.Info;
import com.express.bean.Search_B;
import com.express.model.SearchModel;


public class Search extends Activity {
	private SearchAdapter adapter;
	private List<Search_B> list;
	private EditText edit_pass;
	private ImageView btn_search;
	private String radom_pass;
	private ListView lv;
	ProgressDialog proDialog = null;

    private static final String URL="http://2.expressbox.sinaapp.com/index.php/Home/BoxStatus/radomfind/?radom_pass=";
	
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serch_layout);
		edit_pass=(EditText) this.findViewById(R.id.edit_ra_pass);
		btn_search=(ImageView) this.findViewById(R.id.image_btn);
		 lv=(ListView) this.findViewById(R.id.listView1);
		
		btn_search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
					proDialog = ProgressDialog.show(Search.this, "请稍后",
							"数据加载中..", true, true);
					radom_pass=edit_pass.getText().toString();
					System.out.println(radom_pass);
					init();
				
				
			}
		});
	}

	
		
	
	

	private void init() {
	
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				
				list = SearchModel.getData(URL, radom_pass);
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
		
		adapter = new SearchAdapter(list,this);
		lv.setAdapter(adapter);
		

	}

	
	
	
}
