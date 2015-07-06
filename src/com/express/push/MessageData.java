package com.express.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.express.bean.Info;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessageData {

	private static final String URl = "http://2.expressbox.sinaapp.com/index.php/Admin/tuisong/tscontent";

	public static List<Message> getMessageData() {
		System.out.println("111111eettt");
		List<Message> mess = new ArrayList<Message>();
		InputStream in = null;
		try {
			in = new URL(URl).openStream();
			Gson gson = new Gson();
			mess = gson.fromJson(new InputStreamReader(in),
					new TypeToken<List<Message>>() {
					}.getType());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return mess;
	}

}
