package com.express.main;




/*import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;*/
import com.example.express.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Toast;

@SuppressLint({ "SetJavaScriptEnabled", "ShowToast" })
public class MyLocation extends Fragment {

	/*private LocationClient locationClient = null;
	private static final int UPDATE_TIME = 5000;*/
	ProgressDialog proDialog = null;
	/*private String lat = "";
	private String lng = "";
	private String adress="";*/
	private String Url = "http://3.expressbox.sinaapp.com/index.php/Home/Mark/webapi";
	WebView web=null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View myView = inflater.inflate(R.layout.mapwebviewf, container, false);

		web = (WebView) myView.findViewById(R.id.web_Mapf);

		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setDatabaseEnabled(true);    
		String dir = this.getActivity().getDir("database", Context.MODE_PRIVATE).getPath(); 
		//���ö�λ�����ݿ�·��  
		web.getSettings().setGeolocationDatabasePath(dir);   

		web.setWebChromeClient(new WebChromeClient(){
			public void onGeolocationPermissionsShowPrompt(String origin,   
		               GeolocationPermissions.Callback callback) {  
		    callback.invoke(origin, true, false);  
		    super.onGeolocationPermissionsShowPrompt(origin, callback);  
		} 
		});
		

		/*locationClient = new LocationClient(getActivity());
		// ���ö�λ����
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // �Ƿ��GPS
		option.setCoorType("bd09ll"); // ���÷���ֵ���������͡�
		option.setPriority(LocationClientOption.NetWorkFirst); // ���ö�λ���ȼ�
		option.setProdName("LocationDemo");
		// option.setProdName("com.baidu.location.service_v2.ss");
		// //���ò�Ʒ�����ơ�ǿ�ҽ�����ʹ���Զ���Ĳ�Ʒ�����ƣ����������Ժ�Ϊ���ṩ����Ч׼ȷ�Ķ�λ����
		option.setScanSpan(UPDATE_TIME); // ���ö�ʱ��λ��ʱ��������λ����
		locationClient.setLocOption(option);
		locationClient.start();
		// ע��λ�ü�����
		locationClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				if (location == null) {
					return;
				}
				lat = String.valueOf(location.getLatitude());

				lng = String.valueOf(location.getLongitude());

				System.out.println("����" + lng);
				System.out.println("γ��" + lat);
				if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
					adress=location.getAddrStr();
					System.out.println("weizhi" + adress);
					Toast.makeText(
							getActivity(),
							"�����ڵ�λ�ã�" + location.getAddrStr(),
									 3000).show();
				}

				try {
					if (lng.equals("") || lat.equals("")||adress.equals("")) {

						Thread.sleep(3000);
						if (web != null) {
							web.setWebViewClient(new WebViewClient() {
								@Override
								public void onPageFinished(WebView view, String url) {
									proDialog.dismiss();
								}
							});
						}
						loadUrl(Url + "/?slng=" + lng + "&slat=" + lat+"&adress="+adress);
						System.out.println(Url + "/?slng=" + lng + "&slat="
								+ lat+"&adress="+adress);
						locationClient.stop();
					} else {

						System.out.println("����4" + lng);
						System.out.println("γ��4" + lat);*/
						if (web != null) {
							web.setWebViewClient(new WebViewClient() {
								
								@Override
								public void onPageFinished(WebView view, String url) {
									proDialog.dismiss();
								}
								
							});
							loadUrl(Url);
						}
					
						/*loadUrl(Url + "/?slng=" + lng + "&slat=" + lat+"&adress="+adress);
						System.out.println(Url + "/?slng=" + lng + "&slat="
								+ lat + "eee"+"&adress="+adress);
						locationClient.stop();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onReceivePoi(BDLocation location) {
			}

		});
*/
		return myView;
	}
	
		public void loadUrl(String url) {
			if (web != null) {

				// proDialog = ProgressDialog.show(this,null,"ҳ������У����Ժ�..");
				proDialog = ProgressDialog.show(getActivity(), "",
						"���ݼ�����..���Ժ�....", true, true);
				web.getSettings().setGeolocationEnabled(true); 
				web.getSettings().setDomStorageEnabled(true);
				web.loadUrl(url);

				web.reload();
			}
		}
	/*@Override
	public void onDestroy() {
		super.onDestroy();
		if (locationClient != null && locationClient.isStarted()) {
			locationClient.stop();
			locationClient = null;
		}

	}*/

}
