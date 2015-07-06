package com.express.main;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.express.R;
import com.express.application.Myapplication;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.view.KeyEvent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	LinearLayout main_home_layout, main_query_layout, main_my_layout,
			main_setting_layout;
	ImageView image_main, image_location, image_me, image_set;
	private FragmentManager fragmentManager;
	Main main;
	// Search search;
	MyLocation my;
	NewWorkFail fail;
	MySearch ms;
	Setting set;
	Myapplication myapp;

	IntentFilter intentFilter = null;
	NetworkChangeReceiver changeReceiver = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		netWork();
		setContentView(R.layout.main_layout1);
		// ��apikey�ķ�ʽ��¼��һ�������Activity��onCreate��
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, "kUyxN8al9M4dVosGZPGdFyig");
		main_home_layout = (LinearLayout) this
				.findViewById(R.id.main_home_layout);
		main_query_layout = (LinearLayout) this
				.findViewById(R.id.main_query_layout);
		main_my_layout = (LinearLayout) this.findViewById(R.id.main_my_layout);
		main_setting_layout = (LinearLayout) this
				.findViewById(R.id.main_setting_layout);
		image_main = (ImageView) this.findViewById(R.id.imageview_menu_main);
		image_location = (ImageView) this
				.findViewById(R.id.imageview_menu_location);
		image_me = (ImageView) this.findViewById(R.id.imageview_menu_me);
		image_set = (ImageView) this.findViewById(R.id.imageview_menu_set);
		main_home_layout.setOnClickListener(this);
		main_query_layout.setOnClickListener(this);
		main_my_layout.setOnClickListener(this);
		main_setting_layout.setOnClickListener(this);

		fragmentManager = getFragmentManager();
		myapp = (Myapplication) getApplication();
		// netWork = myapp.detect(MainActivity.this);

		// netWork=new NetWorkChange();

		setTabSelection(0);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.main_home_layout:
			setTabSelection(0);
			break;
		case R.id.main_query_layout:
			setTabSelection(1);
			break;
		case R.id.main_my_layout:

			setTabSelection(2);
			break;
		default:
			setTabSelection(3);
			break;
		}

	}

	public void setTabSelection(int index) {
		clearSelection();
		// FragmentTransaction ר�Ÿ���Fragment�л�
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		hideFragments(transaction);
		System.out.println("����״̬Ϊ��" + myapp.getStatus());
		switch (index) {

		case 0:
			image_main.setImageDrawable(getResources().getDrawable(
					R.drawable.main_part_1_m));

			if (main == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
				main = new Main();
				transaction.add(R.id.content_frame, main);

			} else {
				// ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(main);
			}
			System.out.println("main");
			break;
		case 1:
			image_location.setImageDrawable(getResources().getDrawable(
					R.drawable.main_part_2_l));

			if (my == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������

				if (myapp.getStatus() == 0) {
					// reMoveFragments(transaction,search);
					// setTabSelection(0);
					// transaction.show(main);

					reMoveFragments(transaction);
					Toast.makeText(MainActivity.this, "����������������������ã�", 2000)
							.show();
					System.out.println("weishabu");

				} else {
					my = new MyLocation();
					transaction.add(R.id.content_frame, my);
					System.out.println("my test");
				}

			} else {
				// ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				if (myapp.getStatus() == 0) {
					reMoveFragments(transaction);
					// transaction.add(R.id.content_frame, fail);

				} else {
					transaction.show(my);
				}
			}
			System.out.println("my");
			break;
		case 2:
			image_me.setImageDrawable(getResources().getDrawable(
					R.drawable.main_part_3_w));

			if (ms == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
				System.out.println("ms_one");
				if (myapp.getStatus() == 0) {
					// reMoveFragments(transaction,search);
					// transaction.show(main);
					reMoveFragments(transaction);
					Toast.makeText(MainActivity.this, "����������������������ã�", 2000)
							.show();
					// transaction.add(R.id.content_frame, fail);

					// setTabSelection(0);
					System.out.println("ms_two");
				} else {
					ms = new MySearch();
					transaction.add(R.id.content_frame, ms);
					System.out.println("ms test");
				}

			} else {
				// ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				if (myapp.getStatus() == 0) {

					reMoveFragments(transaction);
					// transaction.add(R.id.content_frame, fail);

				} else {
					System.out.println("ms_there	");
					transaction.show(ms);
				}
			}
			System.out.println("ms");
			break;
		default:
			image_set.setImageDrawable(getResources().getDrawable(
					R.drawable.main_part_4_s));

			if (set == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
				set = new Setting();
				transaction.add(R.id.content_frame, set);
			} else {
				// ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(set);
			}
			System.out.println("set");
			break;

		}

		// ��Ч
		transaction.commit();

	}

	private void hideFragments(FragmentTransaction transaction) {
		if (main != null) {
			transaction.hide(main);
		}
		if (my != null) {
			transaction.hide(my);
		}
		if (ms != null) {
			transaction.hide(ms);
		}
		if (set != null) {
			transaction.hide(set);
		}
		if (fail != null) {
			transaction.hide(fail);
		}
	}

	public void reMoveFragments(FragmentTransaction transaction) {
		if (fail == null) {
			fail = new NewWorkFail();
			transaction.add(R.id.content_frame, fail);
		} else {
			transaction.show(fail);

		}
	}

	private void clearSelection() {
		image_main.setImageDrawable(getResources().getDrawable(
				R.drawable.main_part_1_n));
		image_location.setImageDrawable(getResources().getDrawable(
				R.drawable.main_part_2_n));
		image_me.setImageDrawable(getResources().getDrawable(
				R.drawable.main_part_3_n));
		image_set.setImageDrawable(getResources().getDrawable(
				R.drawable.main_part_4_n));

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showExitDialog();
		}
		return true;
	}

	public void showExitDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ʾ");
		builder.setMessage("��ȷ��Ҫ�˳���������");
		builder.setNegativeButton("��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}

	public void netWork() {
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");// Ҫ���յĹ㲥
		changeReceiver = new NetworkChangeReceiver();
		registerReceiver(changeReceiver, intentFilter);// ע�������
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(changeReceiver);// ȡ��ע��

	}

	class NetworkChangeReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager
					.getActiveNetworkInfo();
			/**
			 * networkInfo.isAvailable(); �Ƿ�����������
			 */
			if (networkInfo != null && networkInfo.isAvailable()) {
				Toast.makeText(context, "������������", 10).show();
				myapp.setStatus(1);

			} else {
				Toast.makeText(context, "��������ʧ��", 10).show();
				myapp.setStatus(0);
			}

		}

	}

}
