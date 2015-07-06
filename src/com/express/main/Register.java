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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity {
   EditText edit_user,edit_pass,edit_rePass,edit_ID;
   Button btn_register,btn_reset;
   String user="";
   String pass="";
   String rePass="";
   String IDCard="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register);
		edit_user=(EditText) this.findViewById(R.id.user_name);
		edit_pass=(EditText) this.findViewById(R.id.password_r1);
		edit_rePass=(EditText) this.findViewById(R.id.password_r2);
		edit_ID=(EditText) this.findViewById(R.id.ed_ID);
		btn_register=(Button) this.findViewById(R.id.btn_register);
		btn_reset=(Button) this.findViewById(R.id.btn_reset);
	
		btn_register.setOnClickListener(new regClick());
		btn_reset.setOnClickListener(new resetClick());
		
		
	}
	
  class regClick implements OnClickListener{

	@Override
	public void onClick(View v) {
		user=edit_user.getText().toString();
		pass=edit_pass.getText().toString();
		rePass=edit_rePass.getText().toString();
		IDCard=edit_ID.getText().toString();
		if(pass.length()!=rePass.length()){
			Toast.makeText(Register.this, "两次密码不一致！", 3000).show();
			edit_rePass.setText("");
		} else if(IDCard.length()!=18){
			Toast.makeText(Register.this, "身份证号不合法！请重新输入！", 3000).show();
			edit_ID.setText("");
		}else{
			new MyDownloadJSONTask().execute();
		}
		
	}
	  
  }
	class resetClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			edit_user.setText("");
			edit_rePass.setText("");
			edit_pass.setText("");
			edit_ID.setText("");
			
		}
		
	}
  //开启异步任务
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
				Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show();
			
				//返回参数
				//保存
				
			   //Intent intent=new Intent(Register.this,Login.class);
			   
				
				//startActivity(intent); // 跳转到成功页面	
				finish();
		    		
			}else{
				Toast.makeText(Register.this, "注册失败！",Toast.LENGTH_SHORT).show();
			}
			
		}
	
  }
	/**
	 * 访问服务器的方法
	 */
  public String getJSONFromServer(){
  	String flag = "";   	
  	HttpClient httpClient = new DefaultHttpClient();
  	HttpPost mPost = new HttpPost("http://2.expressbox.sinaapp.com/index.php/Home/register");
  
		// 传递用户名和密码
  	List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		
		pairs.add(new BasicNameValuePair("pass", pass));
		pairs.add(new BasicNameValuePair("idcard", IDCard));	

		pairs.add(new BasicNameValuePair("phone", user));
	
		
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
						
					
						//user=json.getString("user_name");
						
				  
						System.out.println("flag---------->>是"+flag);
			
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
