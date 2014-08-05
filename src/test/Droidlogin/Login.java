package test.Droidlogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.Droidlogin.library.Httppostaux;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
    /** Called when the activity is first created. */
    
    EditText user;
    EditText pass;
    Button blogin;
    TextView registrar;
    Httppostaux post;
    // String URL_connect="http://10.0.2.2/acces.php";
    String IP_Server="192.168.1.102";
    String URL_connect="http://"+IPAD.ip+"/droidlogin/acces.php";
    int flag;
    CheckBox jizhu = null;
    boolean result_back;
    private ProgressDialog pDialog;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        post=new Httppostaux();
        
        user= (EditText) findViewById(R.id.edusuario);
        pass= (EditText) findViewById(R.id.edpassword);
        blogin= (Button) findViewById(R.id.Blogin);
        registrar=(TextView) findViewById(R.id.link_to_register);
        jizhu=(CheckBox) findViewById(R.id.jizhu);
        
                    
        geneItems();
        blogin.setOnClickListener(new View.OnClickListener(){
       
        	@Override
			public void onClick(View view){

        		String usuario=user.getText().toString();
        		String passw=pass.getText().toString();
        		
        	
        		if( checklogindata( usuario , passw )==true){
        				sendMessage(usuario,passw,String.valueOf(flag));
        			new asynclogin().execute(usuario,passw);        		               
        		}else{
        			err_login();
        		}
        		
        	}
        	});
        
        registrar.setOnClickListener(new View.OnClickListener(){
            
        	@Override
			public void onClick(View view){
        		
        		String url = "http://"+IP_Server+"/droidlogin/adduser.html";
        		Intent i = new Intent(Intent.ACTION_VIEW);
        		i.setData(Uri.parse(url));
        		startActivity(i);        		
        		}        	
    		});
        
        
        jizhu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                if(isChecked){ 
                  flag=1;
                }else{ 
                   flag=0;
                } 
            } 
        }); 
                
    }

    public void err_login(){
    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(200);
	    Toast toast1 = Toast.makeText(getApplicationContext(),"用户名或密码错误", Toast.LENGTH_SHORT);
 	    toast1.show();    	
    }
    
    public boolean loginstatus(String username ,String password ) {
    	int logstatus=-1;  	

    	ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
     		
		    		postparameters2send.add(new BasicNameValuePair("usuario",username));
		    		postparameters2send.add(new BasicNameValuePair("password",password));

      		JSONArray jdata=post.getserverdata(postparameters2send, URL_connect);
      		
		    SystemClock.sleep(950);
		    		
		    //�ǿ�
		    	if (jdata!=null && jdata.length() > 0){

		    		JSONObject json_data; 
					try {
						json_data = jdata.getJSONObject(0); 
						 logstatus=json_data.getInt("logstatus");
						 Log.e("loginstatus","logstatus= "+logstatus);
					} catch (JSONException e) {
						e.printStackTrace();
					}		            
		    		 if (logstatus==0){
		    			 Log.e("loginstatus ", "invalido");
		    			 return false;
		    		 }
		    		 else{
		    			 Log.e("loginstatus ", "valido");
		    			 return true;
		    		 }
			  }else{
		    			 Log.e("JSON  ", "ERROR");
			    		return false;
			  }
    }
    
    public boolean checklogindata(String username ,String password ){
    	
    if 	(username.equals("") || password.equals("")){
    	Log.e("Login ui", "checklogindata user or pass error");
    return false;
    
    }else{
    	
    	return true;
    }
    
}           
    
    
    class asynclogin extends AsyncTask< String, String, String > {
    	 
    	String user,pass;
        @Override
		protected void onPreExecute() {

            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("登陆中....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
		@Override
		protected String doInBackground(String... params) {
			user=params[0];
			pass=params[1];
            
    		if (loginstatus(user,pass)==true){    		    		
    			return "ok"; //login valido
    		}else{    		
    			return "err"; //login invalido     	          	  
    		}
        	
		}
       
        @Override
		protected void onPostExecute(String result) {

           pDialog.dismiss();
           Log.e("onPostExecute=",""+result);
           
           if (result.equals("ok")){

				Intent i=new Intent(Login.this, OpenClose.class);
				IPAD.user = user;
				IPAD.flag=1;
				i.putExtra("user",0);
				startActivity(i); 
				
            }else{
            	err_login();
            }
	}
        }
    
    private void geneItems() {
		 String result = null;
		 JSONArray jArray;
		 StringBuilder sb=null;
		 InputStream is = null;
		 int a=0;
	//http get
    try{
    	 HttpClient httpclient = new DefaultHttpClient();
         HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/getsave.php");                
         List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
         httpget.setEntity(formEntity);
         HttpResponse response = httpclient.execute(httpget);
         HttpEntity entity = response.getEntity();   
         is = entity.getContent();
    }catch(Exception e){
         Log.e("log_tag", "Error in http connection"+e.toString());
    }
    //convert response to string     
    try{
         BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
         sb = new StringBuilder();
         sb.append(reader.readLine() + "\n");

         String line="0";
         while ((line = reader.readLine()) != null) {
              sb.append(line + "\n");
         }
         is.close();
         result=sb.toString();
    }catch(Exception e){
         Log.e("log_tag", "Error converting result "+e.toString());
    }
    String username,password,state;
    try{
         jArray = new JSONArray(result);
         JSONObject json_data=null;
         for(int i=0;i<jArray.length();i++){
              json_data = jArray.getJSONObject(i);
              username=json_data.getString("user");
              password=json_data.getString("pass");
              state=json_data.getString("state");
              if(Integer.valueOf(state)==1)
              {
            	  user.setText(username);
            	  pass.setText(password);
            	  flag = 1;
            	  jizhu.setChecked(true);  
              }
              else{
            	 
            	  flag = 0;
            	  jizhu.setChecked(false);
            	  }

         }
         
    }catch(JSONException e1){
    	 Log.e("log_tag", "Error converting result "+e1.toString());
          Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
    } catch (ParseException e1) {
         e1.printStackTrace();
    }

	 }
    
    private void sendMessage(String paramString1, String paramString2, String paramString3)
    {
      try
      {
    	  HttpClient httpclient = new DefaultHttpClient();
          HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/setsave.php");
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
       
         postParameters.add(new BasicNameValuePair("usuario", paramString1));
         postParameters.add(new BasicNameValuePair("password", paramString2));
         postParameters.add(new BasicNameValuePair("state", paramString3));
       // localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList));
        
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"utf-8");
        httpget.setEntity(formEntity);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();   
        InputStream is = entity.getContent();
        return;
      }
      catch (Exception localException)
      {
        Log.e("log_tag", "Error in http connection" + localException.toString());
      }
    }
 
    }
  

    
 

