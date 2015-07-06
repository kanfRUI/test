package com.express.adapter;

import java.util.List;

import com.example.express.R;
import com.express.bean.Info;
import com.express.push.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {
//hbhgyuhyu
	List<Message> mess;
	Context context;
	LayoutInflater inflater;

	public MessageAdapter(List<Message> mess, Context context) {
		this.mess = mess;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mess.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mess.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Message message = mess.get(position);
		System.out.println("111111eeewww");
		View temp=inflater.inflate(R.layout.newslayout1, null);
			// 数据的绑定
			TextView tv_title=(TextView) temp.findViewById(R.id.tv_style1_title);
			tv_title.setText(message.getTitle());
			TextView tv_content=(TextView) temp.findViewById(R.id.tv_style1_content);
			tv_content.setText(message.getContent());

		
		return temp;
	}
}
