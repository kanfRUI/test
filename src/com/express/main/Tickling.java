package com.express.main;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.express.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tickling extends Activity {
	Button btn_send;
	TextView tv_cilent,tv_sockt;
	EditText edit_say;
	//private static final String Url ="http://2.lgrr.sinaapp.com/tickling.php?say=";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tickling);
		btn_send=(Button) this.findViewById(R.id.btn_send);
		tv_cilent=(TextView) this.findViewById(R.id.text_client);
		tv_sockt=(TextView) this.findViewById(R.id.text_sockt);
		edit_say=(EditText) this.findViewById(R.id.edit_ticking);
		btn_send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String say=edit_say.getText().toString();
				if(say.equals("")){
					Toast.makeText(Tickling.this, "反馈意见不能为空，请输入在提交，谢谢！", 2000).show();
					
				}else{
					tv_cilent.setText(say);
					//new MyAsyncTask().execute(Url+say);
					tv_sockt.setText("你的建议已经收到，非常感谢！");
				}
				
			}
		});
		
		
	}
	/*public String httpGet(String url){
		HttpClient hc=new DefaultHttpClient();
		HttpGet hg=new HttpGet(url);
		String result="";
		try {
			HttpResponse hr=hc.execute(hg);
			if(hr.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				result=EntityUtils.toString(hr.getEntity(),"utf-8");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			hc.getConnectionManager().shutdown();
		}
		return result;
	}
	class MyAsyncTask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return httpGet(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tv_sockt.setText(result);
		}
		
	}*/
	

}
