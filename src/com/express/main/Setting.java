package com.express.main;

import com.example.express.R;


import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Setting extends Fragment implements OnClickListener {
	RelativeLayout re_search,re_Message,re_help,re_tickling,re_we,re_call;
	Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View myView=inflater.inflate(R.layout.main_tab_setting, container, false);
		re_search=(RelativeLayout)myView.findViewById(R.id.re_search);
		re_Message=(RelativeLayout)myView.findViewById(R.id.re_deng);
		re_help=(RelativeLayout)myView.findViewById(R.id.re_help);
		re_tickling=(RelativeLayout)myView.findViewById(R.id.re_tickling);
		re_we=(RelativeLayout)myView.findViewById(R.id.re_we);
		re_call=(RelativeLayout) myView.findViewById(R.id.re_call);
		
		re_search.setOnClickListener(this);
		re_help.setOnClickListener(this);
		re_tickling.setOnClickListener(this);
		re_we.setOnClickListener(this);
		re_Message.setOnClickListener(this);
		re_call.setOnClickListener(this);
		return myView;
	}

	@Override
	public void onClick(View v) {
		int id =v.getId();
		switch(id){
		case R.id.re_search:
			intent=new Intent(getActivity(), Search.class);
			
			startActivity(intent);
			break;
			
		case R.id.re_deng:
			intent=new Intent(getActivity(), MessageActivity.class);
		 
			startActivity(intent);
			break;
		case R.id.re_help:
			intent=new Intent(getActivity(), Help.class);
			
			startActivity(intent);
			break;
		case R.id.re_tickling:
			intent=new Intent(getActivity(), Tickling.class);
			
			startActivity(intent);
			break;
		case R.id.re_we:
			
			break;
        case R.id.re_call:
        	 Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:18819283512"));
        	 startActivity(intent); 
			break;
			
		}
		
	}
	

}
