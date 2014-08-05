package test.Droidlogin;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import test.Droidlogin.R;

import java.io.InputStream;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newGroup extends Activity {
	TextView settitle, logoff;
    JSONArray jArray;
    String result = null;
    InputStream is = null;
    StringBuilder sb=null;
    
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
		 	setContentView(R.layout.new_group);
			
	            final EditText edtInput=(EditText)findViewById(R.id.groupEdit);  
	            final Button Button1 = (Button) findViewById(R.id.Yes);
	            final Button Button2 = (Button) findViewById(R.id.No);
	            Button1.setOnClickListener(new OnClickListener() { 
	            @Override
				public void onClick(View v) {
	            	sendMessage("http://"+IPAD.ip+"/droidlogin/newgroup.php",IPAD.user,edtInput.getText().toString());
	            	Toast.makeText(getBaseContext(), "新分组 "+edtInput.getText().toString()+" 添加成功！" ,Toast.LENGTH_LONG).show();
	            	finish();
	            }
	            });
	            
	            Button2.setOnClickListener(new OnClickListener() {
	            @Override
				public void onClick(View v) {
	            	finish();
	            }
	            });
	 }
		 
		 private void sendMessage(String URL,String user,String newGroup){
		   	 try{
		    	     HttpClient httpclient = new DefaultHttpClient();
		    	     HttpPost httpget = new HttpPost(URL);                
		    	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		    	     user=user+"_group";
		    	     postParameters.add(new BasicNameValuePair("usuario", user));
		 	         postParameters.add(new BasicNameValuePair("newGroup", newGroup));
		 	         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
		 	         httpget.setEntity(formEntity);    
		 	         httpclient.execute(httpget); 
		 	         }catch(Exception e){
		 	        	 Log.e("log_tag", "Error in http connection"+e.toString());
		 	         }
		   	
		   }
	}
    
    
    
 
    

