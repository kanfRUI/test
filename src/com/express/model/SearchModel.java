package com.express.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.express.bean.Info;
import com.express.bean.Search_B;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchModel {
	//private static final String Url = "xxxxxxxx/?radom_pass=";

	public static List<Search_B> getData(String url,String radom_pass) {
		List<Search_B> s_info = new ArrayList<Search_B>();
		InputStream in = null;
		try {
			in = new URL(url + radom_pass).openStream();
			Gson gson = new Gson();
			s_info = gson.fromJson(new InputStreamReader(in),
					new TypeToken<List<Search_B>>() {
					}.getType());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		  }
		return s_info;
		}
}
