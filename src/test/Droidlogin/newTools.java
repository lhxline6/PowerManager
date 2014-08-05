package test.Droidlogin;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;







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

import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.WifiConnect.WifiCipherType;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class newTools extends Activity implements OnClickListener {
	private ScrollView sView;
	private Button openNetCard;
	private Button closeNetCard;
	private Button checkNetCardState;
	private Button scan;
	private Button getScanResult;
	private Button connect;
	private Button disconnect;
	private Button checkNetWorkState;
	private TextView scanResult;
	private String mScanResult;
	private TextView text;
	private WifiAdmin mWifiAdmin;
	private Button first,third,makesure,button;
	private EditText edit1,edit2;
	String receive="ss";
	private ProgressDialog pDialog;
	ProgressDialog mDialog1;
    ProgressDialog mDialog2;
     ProgressDialog dialog;
    private static final int DIALOG1_KEY = 0;
    private static final int DIALOG2_KEY = 1;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newtools);
		mWifiAdmin = new WifiAdmin(newTools.this);
	//	init();
		text = (TextView) findViewById(R.id.title);
		text.setText("添加设备");
		   first = (Button)findViewById(R.id.button1);
	       first.setOnClickListener(new View.OnClickListener()
	       {
	    	 @Override
	         public void onClick(View paramView)
	         {
	    		 connect();
	         }
	       });
	      // disconnect();
	       third = (Button)findViewById(R.id.button3);
	       third.setOnClickListener(new View.OnClickListener()
	       {
	    	 @Override
	         public void onClick(View paramView)
	         {
	    		 connect();
	         }
	       });
	       
	       makesure = (Button)findViewById(R.id.button4);
	       makesure.setOnClickListener(new View.OnClickListener()
	       {
	    	 @Override
	         public void onClick(View paramView)
	         {
	    		 geneItems();
	    		 Toast.makeText(getBaseContext(),"添加成功!",Toast.LENGTH_LONG).show();
	    		 finish();
	         }
	       });
	       
	       edit1= (EditText) findViewById(R.id.editText1);
	       edit2= (EditText) findViewById(R.id.editText2);
	       
	     //  Toast.makeText(getBaseContext(),receive ,Toast.LENGTH_LONG).show();
	       
	       dialog = new ProgressDialog(this);
	        button = (Button) findViewById(R.id.button2);
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	new asynclogin().execute();  
	            	/*
	            	  dialog.setMessage("连接中，请等待...");
		                dialog.setIndeterminate(true);
		                dialog.setCancelable(true);
		            	dialog.show();*/
		          //  	long a =2000000000;
		          //  	while(a>0)
		          //  		a--;
		            //	Thread.sleep(60*1000);
		           // 	 dialog.dismiss();
		            	
	            	
	            	/*
	               
	            	 
				    Socket socket = null;   
				  try {   
				    	socket = new Socket("10.10.100.254",8899);   
				            //获取输出流，用于客户端向服务器端发送数据   
				        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());   
				            //获取输入流，用于接收服务器端发送来的数据   
				        DataInputStream dis = new DataInputStream(socket.getInputStream());   
			            //客户端向服务器端发送数据   
			            dos.writeUTF("*SSID="+edit1.getText().toString()+";#");
			            dos.writeUTF("*PASS="+edit2.getText().toString()+";#"); 
			            //打印出从服务器端接收到的数据   
			            receive=dis.readLine();//.toString();   
			        	dialog.dismiss();
			            //不需要继续使用此连接时，记得关闭哦   
			       //     socket.close();   
			        } catch (UnknownHostException e) {   
			            e.printStackTrace();   
			        } catch (IOException e) {   
			            e.printStackTrace();   
			        }  
	            */}
	   	        });
	       
	   }
	
	
	  class asynclogin extends AsyncTask< String, String, String > {
	    	 
	    	String user,pass;
	        @Override
			protected void onPreExecute() {

	            pDialog = new ProgressDialog(newTools.this);
	            pDialog.setMessage("请等待....");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
	 
			@Override
			protected String doInBackground(String... params) {
				try {
					Thread.currentThread().sleep(10*1000);
				/*	
				    Socket socket = null;   
					socket = new Socket("10.10.100.254",8899);   
		            //获取输出流，用于客户端向服务器端发送数据   
		        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());   
		            //获取输入流，用于接收服务器端发送来的数据   
		        DataInputStream dis = new DataInputStream(socket.getInputStream());   
	            //客户端向服务器端发送数据   
	            dos.writeUTF("*SSID="+edit1.getText().toString()+";#");
	            dos.writeUTF("*PASS="+edit2.getText().toString()+";#"); 
	            //打印出从服务器端接收到的数据   
	            receive=dis.readLine();//.toString();  */
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				
	        	
			}
	       
	        @Override
			protected void onPostExecute(String result) {

	           pDialog.dismiss();
	           Toast.makeText(getBaseContext(),"发送成功！请进入Step 3" ,Toast.LENGTH_LONG).show();
	           
	        
		}
	        }
	
	 private static void sendMessage(String id){
	   	 try{
	    	     HttpClient httpclient = new DefaultHttpClient();
	    	     HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/insert.php");                
	    	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    	     System.out.println("服务器接收到：" +id+" "+IPAD.user+" "); 
	    	     postParameters.add(new BasicNameValuePair("user", IPAD.user));
	    	     postParameters.add(new BasicNameValuePair("id", id));
	    	     postParameters.add(new BasicNameValuePair("name", "插座"+id));
	 	         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
	 	         httpget.setEntity(formEntity);    
	 	         httpclient.execute(httpget); 
	 	         }catch(Exception e){
	 	        	// Log.e("log_tag", "Error in http connection"+e.toString());
	 	         }
	   	
	   }

	
	 private static String geneItems() {
		 String result = null;
		 JSONArray jArray;
		 StringBuilder sb=null;
		 InputStream is = null;
     try{
     	 HttpClient httpclient = new DefaultHttpClient();
     	  
          HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/getid.php");                
          List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
          postParameters.add(new BasicNameValuePair("user", IPAD.user));
          UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
         // formEntity.
          httpget.setEntity(formEntity);
          HttpResponse response = httpclient.execute(httpget);
          HttpEntity entity = response.getEntity();   
          is = entity.getContent();
     }catch(Exception e){
         //.e("log_tag", "Error in http connection"+e.toString());
     }
     try{
          BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
          sb = new StringBuilder();
          sb.append(reader.readLine() + "\n");
 
          String line="0";
          while ((line = reader.readLine()) != null) {
               sb.append(line + "\n");
          }
          is.close();
          result=sb.toString();
     }catch(Exception e){
        //  Log.e("log_tag", "Error converting result "+e.toString());
     }
     String id = null;
     
     try{	 
          jArray = new JSONArray(result);
          JSONObject json_data=null;
          for(int i=0;i<jArray.length();i++){
        	  json_data = jArray.getJSONObject(i);
        	  id=json_data.getString("id");  
        	  sendMessage(id);
        	  
          }         
     }catch(JSONException e1){
     	 Log.e("log_tag", "Error converting result "+e1.toString());
    
     } catch (ParseException e1) {
          e1.printStackTrace();
     }
	  

     return id;
	 }
	public void init() {
		sView = (ScrollView) findViewById(R.id.mScrollView);
	/*	openNetCard = (Button) findViewById(R.id.openNetCard);
		closeNetCard = (Button) findViewById(R.id.closeNetCard);
		checkNetCardState = (Button) findViewById(R.id.checkNetCardState);
		scan = (Button) findViewById(R.id.scan);
		getScanResult = (Button) findViewById(R.id.getScanResult);
		scanResult = (TextView) findViewById(R.id.scanResult);
		connect = (Button) findViewById(R.id.connect);
		disconnect = (Button) findViewById(R.id.disconnect);
		checkNetWorkState = (Button) findViewById(R.id.checkNetWorkState);*/

		openNetCard.setOnClickListener(newTools.this);
		closeNetCard.setOnClickListener(newTools.this);
		checkNetCardState.setOnClickListener(newTools.this);
		scan.setOnClickListener(newTools.this);
		getScanResult.setOnClickListener(newTools.this);
		connect.setOnClickListener(newTools.this);
		disconnect.setOnClickListener(newTools.this);
		checkNetWorkState.setOnClickListener(newTools.this);
	}

	
	 @Override
	    protected Dialog onCreateDialog(int id) {
	        switch (id) {
	            case DIALOG1_KEY: {
	                ProgressDialog dialog = new ProgressDialog(this);
	                dialog.setTitle("Indeterminate");
	                dialog.setMessage("Please wait while loading...");
	                dialog.setIndeterminate(true);
	                dialog.setCancelable(true);
	                return dialog;
	            }
	            case DIALOG2_KEY: {
	                ProgressDialog dialog = new ProgressDialog(this);
	                dialog.setMessage("Please wait while loading...");
	                dialog.setIndeterminate(true);
	                dialog.setCancelable(true);
	                return dialog;
	            }
	        }
	        return null;
	    }
	/**
	 * WIFI_STATE_DISABLING 0 WIFI_STATE_DISABLED 1 WIFI_STATE_ENABLING 2
	 * WIFI_STATE_ENABLED 3
	 */
	public void openNetCard() {
		mWifiAdmin.openNetCard();
	}

	public void closeNetCard() {
		mWifiAdmin.closeNetCard();
	}

	public void checkNetCardState() {
		mWifiAdmin.checkNetCardState();
	}

	public void scan() {
		mWifiAdmin.scan();
	}

	public void getScanResult() {
		mScanResult = mWifiAdmin.getScanResult();
		scanResult.setText(mScanResult);
	}

	public void connect() {
		mWifiAdmin.connect();
//		startActivityForResult(new Intent(
//				android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
		startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
	}

	public void disconnect() {
		mWifiAdmin.disconnectWifi();
	}

	public void checkNetWorkState() {
		mWifiAdmin.checkNetWorkState();
	}

	@Override
	public void onClick(View v) {
		/*	switch (v.getId()) {
		case R.id.openNetCard:
			openNetCard();
			break;
		case R.id.closeNetCard:
			closeNetCard();
			break;
		case R.id.checkNetCardState:
			checkNetCardState();
			break;
		case R.id.scan:
			scan();
			break;
		case R.id.getScanResult:
			getScanResult();
			break;
		case R.id.connect:
			connect();
			break;
		case R.id.disconnect:
			disconnect();
			break;
		case R.id.checkNetWorkState:
			checkNetWorkState();
			break;
		default:
			break;
		}*/
	}
}
