package com.express.main;

import com.example.express.R;
import com.example.express.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Fragment implements OnClickListener {
	ImageView image_login, image_yunda, image_sto, image_zto, image_sf,
			image_yt, image_bai, image_tt, image_jia;
	Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.main, container, false);
		image_login = (ImageView) myView.findViewById(R.id.image_login);
		image_yunda = (ImageView) myView.findViewById(R.id.image_yunda);
		image_sto = (ImageView) myView.findViewById(R.id.image_sto);
		image_sf = (ImageView) myView.findViewById(R.id.image_sf);
		image_yt = (ImageView) myView.findViewById(R.id.image_yt);
		image_bai = (ImageView) myView.findViewById(R.id.image_di);
		image_tt = (ImageView) myView.findViewById(R.id.image_tt);
		image_zto = (ImageView) myView.findViewById(R.id.image_zto);
		image_jia = (ImageView) myView.findViewById(R.id.image_jia);
		image_login.setOnClickListener(this);
		image_yunda.setOnClickListener(this);
		image_sto.setOnClickListener(this);
		image_sf.setOnClickListener(this);
		image_yt.setOnClickListener(this);
		image_bai.setOnClickListener(this);
		image_tt.setOnClickListener(this);
		image_zto.setOnClickListener(this);
		image_jia.setOnClickListener(this);

		return myView;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.image_login:
			intent=new Intent(getActivity(),Login.class);
			startActivity(intent);
			getActivity().finish();
			break;
		case R.id.image_yunda:
			intent=new Intent(getActivity(),WebViewData.class);
			intent.putExtra("Url","http://m.kuaidi100.com/all/yd.htm" );
			startActivity(intent);

			break;
		case R.id.image_sto:
			intent=new Intent(getActivity(),WebViewData.class);
			intent.putExtra("Url","http://m.kuaidi100.com/all/st.htm?from=openv" );
			startActivity(intent);

			break;
		case R.id.image_sf:
			intent=new Intent(getActivity(),WebViewData.class);
			intent.putExtra("Url","http://m.kuaidi100.com/all/sf.htm" );
			startActivity(intent);
			break;
		case R.id.image_yt:
			intent=new Intent(getActivity(),WebViewData.class);
			intent.putExtra("Url","http://m.kuaidi100.com/all/yt.htm" );
			startActivity(intent);
			break;
		case R.id.image_di:
			intent=new Intent(getActivity(),WebViewData.class);
			intent.putExtra("Url","http://m.kuaidi100.com/all/yzgn.htm" );
			startActivity(intent);
			break;
		case R.id.image_tt:
			intent=new Intent(getActivity(),WebViewData.class);
			intent.putExtra("Url","http://m.kuaidi100.com/all/tt.htm" );
			startActivity(intent);
			break;
		case R.id.image_zto:
			intent=new Intent(getActivity(),WebViewData.class);
			intent.putExtra("Url","http://m.kuaidi100.com/all/zt.htm" );
			startActivity(intent);

			break;
		case R.id.image_jia:
			Toast.makeText(getActivity(), "待开发中，请期待！", 1000).show();

			break;

		
		}

	}

}
