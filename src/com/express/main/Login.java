package com.express.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.express.R;
import com.express.application.Myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



public class Login extends Activity {
	
	private static final String PREFS_NAME = "MyUserInfo";
	private static final String PREFS_NAME1 = "MyUser";
	private static  int ST=0; 
	Button login_button, register_button;
	EditText username1, password1;
	CheckBox rememberPwd,Autologon;
	//String number = "";
	String pass = "";
	String user_name = "";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login);
		
		username1 =(EditText)this.findViewById(R.id.username);
		password1 = (EditText)this.findViewById(R.id.password);
		login_button = (Button) this.findViewById(R.id.btn_login);
		register_button=(Button) this.findViewById(R.id.btn_register1);
		rememberPwd = (CheckBox) this.findViewById(R.id.rememberPwd);
		Autologon = (CheckBox) this.findViewById(R.id.Autologon);
		
         register_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Login.this,Register.class);
				startActivity(intent);
			}
		});

		
		LoadUserDate();
		loadAutologon();
		// 登陆
		if(Autologon.isChecked()){
			ST=1;
			new MyDownloadJSONTask().execute();	
			
			
		}
		login_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ST=0;                         
				new MyDownloadJSONTask().execute();	
				SaveUserDate();
			}
		});
		
		
	}
	
	/**
	 * 保存用户信息
	 */
	private void SaveUserDate() {
		// 载入配置文件
		SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);
		// 写入配置文件
		Editor spEd = sp.edit();
		if (rememberPwd.isChecked()) {
			spEd.putBoolean("isSave", true);
			spEd.putString("user_name", username1.getText().toString());
			spEd.putString("password", password1.getText().toString());
		} else {
			spEd.putBoolean("isSave", false);
			spEd.putString("user_name", "");
			spEd.putString("password", "");
		}
		spEd.commit();
	}
	/**
	 * 保存自动登录的用户信息
	 */
	private void saveAutologon() {
		// 载入配置文件
		SharedPreferences sp = getSharedPreferences(PREFS_NAME1, 0);
		// 写入配置文件
		Editor spEd = sp.edit();
		if (Autologon.isChecked()) {
			spEd.putBoolean("isAutologon", true);
			spEd.putString("user_name", user_name);
			spEd.putString("password", pass);
		}else {
			spEd.putBoolean("isAutologon", false);
			spEd.putString("user_name", "");
			spEd.putString("password", "");
		} 
		spEd.commit();
	}


	/**
	 * 判断是否自动登录
	 */
	private void loadAutologon() {
		SharedPreferences sp = getSharedPreferences(PREFS_NAME1, 0);

		if (sp.getBoolean("isAutologon", false)) {
			String username1 = sp.getString("user_name", "");
			String userpassword1 = sp.getString("password", "");
			if (!("".equals(username1) && "".equals(userpassword1))) {
				user_name=username1;
				pass=userpassword1;
				Autologon.setChecked(true);
			}
		}
	}
	/**
	 * 载入已记住的用户信息
	 */
	private void LoadUserDate() {
		SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);

		if (sp.getBoolean("isSave", false)) {
			String username = sp.getString("user_name", "");
			String userpassword = sp.getString("password", "");
			if (!("".equals(username) && "".equals(userpassword))) {
				username1.setText(username);
				password1.setText(userpassword);
				rememberPwd.setChecked(true);
			}
		}
	}
	/**
	 * 判断用户登录返回信息
	 */
	
	public class MyDownloadJSONTask extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... Params) {
			// TODO Auto-generated method stub
			return getJSONFromServer();
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			System.out.println("Result成功---------->>是"+result);
			
			if(result.equals("1")){
				Toast.makeText(Login.this, "登录成功！", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Login.this, MainActivity.class);
				//返回参数
				//保存
				System.out.println(pass);
				saveAutologon();
				Myapplication myApp = (Myapplication) getApplication();
				myApp.setName(user_name);						
				
		       startActivity(intent); // 跳转到成功页面	
				finish();
			}else{
				Toast.makeText(Login.this, "用户不存在或密码错误", Toast.LENGTH_SHORT).show();
			}
			
		}
	
    }
	/**
	 * 访问服务器的方法
	 */
    public String getJSONFromServer(){
    	String flag = "";   	
    	HttpClient httpClient = new DefaultHttpClient();
    	HttpPost mPost = new HttpPost("http://2.expressbox.sinaapp.com/index.php/Home/Login/index");
    	//HttpPost mPost = new HttpPost("http://1.lqs930920.sinaapp.com/user_login.php");
    	//HttpPost mPost = new HttpPost("http://1.lqs930920.sinaapp.com/news.php");
		// 传递用户名和密码
    	List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		String username="" ;
		String pass1="";
		if(ST==0){
			System.out.println("aaaas");
			username = username1.getText().toString();
			System.out.println("sss"+username);
			 pass1 = password1.getText().toString();
			 System.out.println("sss"+pass1);
			pairs.add(new BasicNameValuePair("pass", pass1));
			
		}else{
			System.out.println("ccccs");
			username =user_name;
			pass1 =pass;
			pairs.add(new BasicNameValuePair("pass", pass1));
		}
		
		
		pairs.add(new BasicNameValuePair("phone", username));
	
		
		try {
			mPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
			HttpResponse response = httpClient.execute(mPost);
			int res = response.getStatusLine().getStatusCode();
			if (res == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String Result = EntityUtils.toString(entity,"utf-8");
					try {
						System.out.println("Result1---------->>是"+Result+"长度是"+Result.length());
						int start=Result.indexOf("{");
						int stop=Result.lastIndexOf("}");
						String temp=Result.substring(start, stop+1);
						System.out.println("Result2---------->>是"+temp+"长度是"+temp.length());
						
						JSONObject json = new JSONObject(temp);
						flag = json.getString("status");
						//user_name = json.getString("number");
						pass = json.getString("pass");
						user_name=json.getString("phone");
						
				   /*    classnum=json.getString("class")	;
				       phone=json.getString("phone");
				       email=json.getString("email");
				       address=json.getString("address");
				       company=json.getString("company");*/
						System.out.println("user_name---------->>是"+user_name);
						System.out.println("pass---------->>是"+pass);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return flag;
    }
	
	


}
