package com.express.adapter;

import java.util.List;

import com.example.express.R;
import com.express.bean.Info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MySearchAdapter extends BaseAdapter {

	List<Info> info;
	Context context;
	LayoutInflater inflater;

	public MySearchAdapter(List<Info> info, Context context) {
		this.info = info;
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
		Info in = info.get(position);
		View temp=null;
		if (in.getStatus() == 2) {
			temp=inflater.inflate(R.layout.mysearch_item1, null);
		}else{
			
		
			temp=inflater.inflate(R.layout.mysearch_item2, null);
			// 数据的绑定
			TextView phone = (TextView) temp
					.findViewById(R.id.tv_phone1);
			phone.setText("电话:"+in.getPhone()+"");

			TextView address = (TextView) temp
					.findViewById(R.id.tv_adress1);
			address.setText("地址:"+in.getAdress());
			TextView boxnumber = (TextView) temp
					.findViewById(R.id.tv_num1);
			boxnumber.setText("柜号:"+in.getBoxnumber());
			TextView put_time = (TextView) temp
					.findViewById(R.id.tv_date1);
			put_time.setText("时间:"+in.getPut_time());
			TextView mail_company = (TextView) temp
					.findViewById(R.id.tv_company1);
			mail_company.setText("公司:"+in.getCompany());
			TextView radom_pass = (TextView) temp
					.findViewById(R.id.tv_pass1);
			radom_pass.setText("短信密码:"+in.getRadom_pass());
			

		}

		return temp;
	}
}
