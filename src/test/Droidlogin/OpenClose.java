package test.Droidlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import test.Droidlogin.R;

























import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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

import test.Droidlogin.ListItem;
import test.Droidlogin.MyAdapter;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;
/*��ӭ��Ļ*/
public class OpenClose extends Activity  {
	TextView settitle, logoff;
	//static final private int STATE_ID = Menu.FIRST;
    static final private int SETTINGS_ID = Menu.FIRST + 1;
    static final private int RENAME_ID = Menu.FIRST + 2;
    static final private int MANAGE_ID = Menu.FIRST + 3;

    
    private ArrayList<ListItem> list_GroupItem;
    private ListView listView;
   
    public static OpenClose instance = null;
    ImageButton imgBtn01;
    Button bt1,bt2,bt3;

	 @Override
	public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
		 	setContentView(R.layout.am);
		 	instance = this;

			listView = (ListView)findViewById(R.id.listView1);
			listView.setDivider(null);
	        list_GroupItem = new ArrayList<ListItem>();
	        
	        MyAdapter mAdapter_ListGroup = new MyAdapter(this, list_GroupItem);
			mAdapter_ListGroup.AddType(R.layout.list_item2); //toggle 关
			mAdapter_ListGroup.AddType(R.layout.list_item4);
			mAdapter_ListGroup.AddType(R.layout.list_item5); //toggle 开
			listView.setAdapter(mAdapter_ListGroup);
			geneGroups(IPAD.user);
	/*
			 new Thread( new Runnable() {     
	        	    public void run() {     
	        	    	try
	        	        {	
	        	    		while(true){
	        	    		Thread.sleep(30*1000);
	        	    		//instance.finish();
	        	    		while(IPAD.stop==1){
	        	    			finish();
	        	    			IPAD.stop=0;
	        	    			}
	        	    		if(IPAD.flag==1){
	        	    		//	instance.finish();
	        	    			
	        	    		Intent i2=new Intent(OpenClose.this, OpenClose.class);
	    					i2.putExtra("user",0);
	    					startActivity(i2); 
	    		           // finish();
	    					finish();
	        	    		}
	        	    		}
	        	        }
	        	        catch (Exception localException)
	        	        {
	        	          Log.e("log_tag", "Error in http connection" + localException.toString());
	        	     }  
	        	    	}          
	        	}).start();*/
			
			imgBtn01 = (ImageButton)findViewById(R.id.imageButton1);
			imgBtn01.setOnClickListener(new OnClickListener() {  
		        @Override  
		        public void onClick(View v) {  
		          // myTextView.setText("ImageButton的监听事件");  
		        	Intent i2=new Intent(OpenClose.this, OpenClose.class);
					i2.putExtra("user",0);
					startActivity(i2); 
		            finish();
		        }  
		      }); 
			bt1 = (Button)findViewById(R.id.button1);
			bt1.setOnClickListener(new OnClickListener() {  
		        @Override  
		        public void onClick(View v) {  
		          // myTextView.setText("ImageButton的监听事件");  
		        	Intent i2=new Intent(OpenClose.this, OpenClose.class);
					i2.putExtra("user",0);
					startActivity(i2); 
		            finish();
		        }  
		      }); 
			bt2 = (Button)findViewById(R.id.button2);
			bt2.setOnClickListener(new OnClickListener() {  
		        @Override  
		        public void onClick(View v) {  
		          // myTextView.setText("ImageButton的监听事件");  
		        	Intent i2=new Intent(OpenClose.this, DateManager.class);
					i2.putExtra("user",0);
					startActivity(i2); 
					IPAD.flag=0;
		            finish();
		        }  
		      }); 
			bt3 = (Button)findViewById(R.id.button3);
			bt3.setOnClickListener(new OnClickListener() {  
		        @Override  
		        public void onClick(View v) {  
		          // myTextView.setText("ImageButton的监听事件");  
		        	Intent i2=new Intent(OpenClose.this, Settings.class);
					i2.putExtra("user",0);
					startActivity(i2); 
					IPAD.flag=0;
		            finish();
		        }  
		      }); 
	 }
	 /* @Override  
	  
	 public void onResume() {  
	   
		 geneGroups(IPAD.user);
		 
	 super.onResume();  
	   
	 //代码  
	   
	 } */ 
	 private void geneGroups(String user) {
		 StringBuilder sb=null;
		 InputStream is = null;
		 String result = null;
		 JSONArray jArray;
         try{
        	
         	 HttpClient httpclient = new DefaultHttpClient();
              HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/group.php");                
              List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
              String userGroup=user+"_group";
              postParameters.add(new BasicNameValuePair("group", userGroup));
       
              UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
              httpget.setEntity(formEntity);
              HttpResponse response = httpclient.execute(httpget);
              HttpEntity entity = response.getEntity();   
              is = entity.getContent();
         }catch(Exception e){
              Log.e("log_tag", "Error in http connection"+e.toString());
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
              Log.e("log_tag", "Error converting result "+e.toString());
         }
         int num;
         String ct_name;
         try{
              jArray = new JSONArray(result);
              JSONObject json_data=null;
              list_GroupItem.clear();
              for(int i=0;i<jArray.length();i++){
                   json_data = jArray.getJSONObject(i);
                   ct_name=json_data.getString("socketgroup");
                   num=json_data.getInt("num");
                   if(num!=0){
                	//list_GroupItem.add(new ListItem(0,getHashMap0(1,ct_name)));
                	   list_GroupItem.add(new ListItem(1, getHashMap2(ct_name)));
      				 	geneItems(user,ct_name);
      				 	
      				 }
                 
              }
         }catch(JSONException e1){
         	 Log.e("log_tag", "Error converting result "+e1.toString());
              Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
         } catch (ParseException e1) {
              e1.printStackTrace();
         }
       }
 
	 private void geneItems(String user,String group) {
		 String result = null;
		 JSONArray jArray;
		 StringBuilder sb=null;
		 InputStream is = null;
     try{
     	 HttpClient httpclient = new DefaultHttpClient();
     	  
          HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/test.php");                
          List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
          postParameters.add(new BasicNameValuePair("usuario", user));
          postParameters.add(new BasicNameValuePair("socketgroup", group));
          UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
         // formEntity.
          httpget.setEntity(formEntity);
          HttpResponse response = httpclient.execute(httpget);
          HttpEntity entity = response.getEntity();   
          is = entity.getContent();
     }catch(Exception e){
          Log.e("log_tag", "Error in http connection"+e.toString());
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
          Log.e("log_tag", "Error converting result "+e.toString());
     }
     String ct_name;
     int state;
     try{	 
          jArray = new JSONArray(result);
          JSONObject json_data=null;
          for(int i=0;i<jArray.length();i++){
        	  json_data = jArray.getJSONObject(i);
              ct_name=json_data.getString("name");
              state=json_data.getInt("state"); 
              if(state==0){
	              ListItem a = new ListItem(0, getHashMap1(ct_name));
	              list_GroupItem.add(a);
	              }
              else {
            	  ListItem  b = new ListItem(2, getHashMap3(ct_name));
            	  list_GroupItem.add(b);
            	  }	
          }         
     }catch(JSONException e1){
     	 Log.e("log_tag", "Error converting result "+e1.toString());
           Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
     } catch (ParseException e1) {
          e1.printStackTrace();
     }

	 }

	    private HashMap<Integer, Object> getHashMap1(String s) {	
			HashMap<Integer, Object> map1 = new HashMap<Integer, Object>();
			map1.put(R.id.textView, s);
			map1.put(R.id.item_id, "   ");
			return map1;
		}

	    
	    private HashMap<Integer, Object> getHashMap2(String s) {	
			HashMap<Integer, Object> map1 = new HashMap<Integer, Object>();
			map1.put(R.id.textView2, "");
			map1.put(R.id.item_id2, s);
			return map1;
		}
	    
	    private HashMap<Integer, Object> getHashMap3(String s) {	
			HashMap<Integer, Object> map1 = new HashMap<Integer, Object>();
			map1.put(R.id.textView3, "");
			map1.put(R.id.item_id3, s);
			return map1;
		}
	
	    public void btnClick2(View v) throws UnknownHostException, IOException{
	    	LinearLayout layout=(LinearLayout)v.getParent(); 
	    	String text1=((TextView)layout.findViewById(R.id.item_id2)).getText().toString();
            sendMessage("http://"+IPAD.ip+"/droidlogin/open.php",IPAD.user,text1,"0",null);
            //1.�����ͻ���socket���ӣ�ָ��������λ�ü��˿�
            Socket socket =new Socket("192.168.1.100",8899);
            //2.�õ�socket��д��
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os);
            //������
            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            //3.����������һ���Ĳ�������socket���ж�д����    
              pw.write("a", 0, 1);
               pw.flush();
            Intent i=new Intent(OpenClose.this, OpenClose.class);
			startActivity(i); 
            finish();
	    }
	    
	   
	    
		 public void btnClick(View v){
		    	LinearLayout layout=(LinearLayout)v.getParent(); 
		 
		    	String text1=((TextView)layout.findViewById(R.id.textView)).getText().toString();
		    	ToggleButton a =((ToggleButton)layout.findViewById(R.id.toggleButton1));	
		    	if (a.isChecked()) {              
		    		 Toast.makeText(OpenClose.this, text1+"已开启", Toast.LENGTH_SHORT).show();       
		                sendMessage("http://"+IPAD.ip+"/droidlogin/open.php",IPAD.user,text1,"1",null);
		    		

             	 try {
		    	            Socket socket =new Socket("192.168.1.100",8899);
		    	            //2.�õ�socket��д��
		    	            OutputStream os=socket.getOutputStream();
		    	            PrintWriter pw=new PrintWriter(os);
		    	            //������
		    	            InputStream is=socket.getInputStream();
		    	            BufferedReader br=new BufferedReader(new InputStreamReader(is));
		    	            //3.����������һ���Ĳ�������socket���ж�д����
		    	                	
		    	                 pw.write("b", 0, 1);	    	                	 
		    	                	
		    	                 pw.flush();
		    	                 
		    	    } catch (UnknownHostException e) {
		    	        e.printStackTrace();
		    	    } catch (IOException e) {
		    	        e.printStackTrace();
		    	    }      
                }   
                else { 
                	 Toast.makeText(OpenClose.this, text1+"已关闭", Toast.LENGTH_SHORT).show();       
		                sendMessage("http://"+IPAD.ip+"/droidlogin/open.php",IPAD.user,text1,"0",null);
                	 try {
		    	            //1.�����ͻ���socket���ӣ�ָ��������λ�ü��˿�
		    	            Socket socket =new Socket("192.168.1.100",8899);
		    	            //2.�õ�socket��д��
		    	            OutputStream os=socket.getOutputStream();
		    	            PrintWriter pw=new PrintWriter(os);
		    	            //������
		    	            InputStream is=socket.getInputStream();
		    	            BufferedReader br=new BufferedReader(new InputStreamReader(is));
		    	            //3.����������һ���Ĳ�������socket���ж�д����    
		    	              pw.write("a", 0, 1);
		    	               pw.flush();
		    	    } catch (UnknownHostException e) {
		    	        e.printStackTrace();
		    	    } catch (IOException e) {
		    	        e.printStackTrace();
		    	    } 
                }      
		    }
		 
		 
		 public void btnClick3(View v){
		    	LinearLayout layout=(LinearLayout)v.getParent(); 
		 
		    	String text1=((TextView)layout.findViewById(R.id.item_id3)).getText().toString();
		    	ToggleButton a =((ToggleButton)layout.findViewById(R.id.toggleButton3));     
		    	
		    	if (a.isChecked()) {              
                 Toast.makeText(OpenClose.this, text1+"已开启", Toast.LENGTH_SHORT).show();       
                 sendMessage("http://"+IPAD.ip+"/droidlogin/open.php",IPAD.user,text1,"1",null);
                 try {
	    	            Socket socket =new Socket("192.168.1.100",8899);
	    	            //2.�õ�socket��д��
	    	            OutputStream os=socket.getOutputStream();
	    	            PrintWriter pw=new PrintWriter(os);
	    	            //������
	    	            InputStream is=socket.getInputStream();
	    	            BufferedReader br=new BufferedReader(new InputStreamReader(is));
	    	            //3.����������һ���Ĳ�������socket���ж�д����
	    	                	
	    	                 pw.write("b", 0, 1);	    	                	 
	    	                	
	    	                 pw.flush();
	    	                 
	    	    } catch (UnknownHostException e) {
	    	        e.printStackTrace();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }      
             }   
             else {              
                 Toast.makeText(OpenClose.this, text1+"已关闭", Toast.LENGTH_SHORT).show(); 
                 sendMessage("http://"+IPAD.ip+"/droidlogin/open.php",IPAD.user,text1,"0",null);
                 try {
	    	            //1.�����ͻ���socket���ӣ�ָ��������λ�ü��˿�
	    	            Socket socket =new Socket("192.168.1.100",8899);
	    	            //2.�õ�socket��д��
	    	            OutputStream os=socket.getOutputStream();
	    	            PrintWriter pw=new PrintWriter(os);
	    	            //������
	    	            InputStream is=socket.getInputStream();
	    	            BufferedReader br=new BufferedReader(new InputStreamReader(is));
	    	            //3.����������һ���Ĳ�������socket���ж�д����    
	    	              pw.write("a", 0, 1);
	    	               pw.flush();
	    	    } catch (UnknownHostException e) {
	    	        e.printStackTrace();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    } 
             	
            
             }      
		    }
		
		 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
		        super.onCreateOptionsMenu(menu);
		   //     menu.add(0, STATE_ID, 0, R.string.state).setShortcut('0', 'b');
		        menu.add(0, SETTINGS_ID, 0, R.string.settings).setShortcut('1', 'c');
		        menu.add(0, MANAGE_ID, 0, R.string.manage).setShortcut('2', 'd');

		        return true;
		    }
		
		 @Override
		    public boolean onOptionsItemSelected(MenuItem item) {
		        switch (item.getItemId()) {

		        case SETTINGS_ID:  
		        	Intent i1=new Intent(OpenClose.this, Settings.class);
					
					startActivity(i1); 
		      
		            return true;
		        case RENAME_ID:        
		            return true;
		        case MANAGE_ID:
		            finish();
		            return true;
		        }
		        return super.onOptionsItemSelected(item);
		    } 
		 
		 private void sendMessage(String URL,String user,String socket,String state,String name){
		   	 try{
		    	     HttpClient httpclient = new DefaultHttpClient();
		    	     HttpPost httpget = new HttpPost(URL);                
		    	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		    	     postParameters.add(new BasicNameValuePair("usuario", user));
		 	         postParameters.add(new BasicNameValuePair("socket", socket));
		 	         if(state!=null)
		 	         postParameters.add(new BasicNameValuePair("state", state));
		 	         if(name!=null)
		 	        	 postParameters.add(new BasicNameValuePair("name", name));
		 	         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
		 	         httpget.setEntity(formEntity);    
		 	         httpclient.execute(httpget); 
		 	         }catch(Exception e){
		 	        	 Log.e("log_tag", "Error in http connection"+e.toString());
		 	         }
		   	
		   }
		 
		 
	}
