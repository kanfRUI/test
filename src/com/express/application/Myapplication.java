package com.express.application;

import com.baidu.frontia.FrontiaApplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Myapplication extends FrontiaApplication {
	public  String NAME = "me";
	private int status = 2;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void onCreate() {

		super.onCreate();
		setName(NAME);
		setStatus(status);
	
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
