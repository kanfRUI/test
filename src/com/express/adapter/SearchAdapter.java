package com.express.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.example.express.*;

import com.express.bean.Info;
import com.express.bean.Search_B;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SearchAdapter extends BaseAdapter {
	List<Search_B> info;
	Context context;
	LayoutInflater inflater;

	public SearchAdapter(List<Search_B> list, Context context) {
		this.info = list;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return info.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Search_B in = info.get(position);
		View temp=null;
		if (in.getStatus() == 2) {
			temp=inflater.inflate(R.layout.search_item1, null);
			

		}else{
			
			
			temp=inflater.inflate(R.layout.search_item2, null);
			// 数据的绑定
			

			TextView address = (TextView) temp
					.findViewById(R.id.tv_adress);
			address.setText("地址:"+in.getAdress());
			TextView boxnumber = (TextView) temp
					.findViewById(R.id.tv_num);
			boxnumber.setText("柜号:"+in.getBoxnumber());
			TextView put_time = (TextView) temp
					.findViewById(R.id.tv_date);
			put_time.setText("时间:"+in.getPut_time());
		}

		return temp;
	}

	
}
