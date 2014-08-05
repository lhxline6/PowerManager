package test.Droidlogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import test.Droidlogin.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity {

	String user;
	private ArrayList<ListItem> list_GroupItem;
    private ListView listView;
	SimpleAdapter adapter;
	public int MID;
//	private XListView sListView;
	private TextView text;
	 Button bt1,bt2,bt3;
	 private ListView areaCheckListView;  
	    private String[] areas;  
	    private boolean[] areaState;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView)findViewById(R.id.listView1);
		listView.setDivider(null);
        list_GroupItem = new ArrayList<ListItem>();
        
        MyAdapter mAdapter_ListGroup = new MyAdapter(this, list_GroupItem);
		mAdapter_ListGroup.AddType(R.layout.list_item6); //toggle 关
	//	mAdapter_ListGroup.AddType(R.layout.list_item4);
	//	mAdapter_ListGroup.AddType(R.layout.list_item5); //toggle 开
		listView.setAdapter(mAdapter_ListGroup);
		  
		//sListView = (XListView) findViewById(R.id.xListView);
		text = (TextView) findViewById(R.id.title);
		text.setText("设置");
		FillListData();
		getItem(IPAD.user);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int position, long id) {		
				switch (position) {
				case 0:
					Intent intent = new Intent(Settings.this, ReName.class);		        	
					startActivity(intent); 	
					break;
				case 1:
					Intent i1 = new Intent(Settings.this, SetGroup.class);
					startActivity(i1); 
					break;
				case 2:
					showDialog_Layout(Settings.this,"新组名");
					break;
				case 3:
					Intent i2 = new Intent(Settings.this, newTools.class);
					startActivity(i2); 
					break;
				case 4:
					Intent i3 = new Intent(Settings.this, Check.class);
					startActivity(i3); 
					break;
				case 5:
					showDialog_Layout(Settings.this,"新密码");
					break;
				case 6:
					//showDialog_Layout(Settings.this,"新密码");
					AlertDialog ad = new AlertDialog.Builder(Settings.this)  
			    	   .setTitle("选择区域")  
			    	   .setMultiChoiceItems(areas,areaState,new DialogInterface.OnMultiChoiceClickListener(){  
			    	      @Override
						public void onClick(DialogInterface dialog,int whichButton, boolean isChecked){  
			    	       //点击某个区域  
			    	      }  
			    	     }).setPositiveButton("确定",new DialogInterface.OnClickListener(){  
			    	      @Override
						public void onClick(DialogInterface dialog,int whichButton){  
			    	       for (int i = 0; i < areas.length; i++){  
			    	        if (areaCheckListView.getCheckedItemPositions().get(i)){  
			    	//         s += areaCheckListView.getAdapter().getItem(i)+ ";"; 
			    	 //        todo[i]=(String) areaCheckListView.getAdapter().getItem(i);
			    	        	send( areaCheckListView.getAdapter().getItem(i).toString());
			    	        }else{  
			    	         areaCheckListView.getCheckedItemPositions().get(i,false);  
			    	        }  
			    	       }  
			    	       if (areaCheckListView.getCheckedItemPositions().size() > 0){  
			    	       // choose.setText(s);
			    	       }else{  
			    	         //没有选择  
			    	       }  
			    	       dialog.dismiss();  
			    	      }  
			    	     }).setNegativeButton("取消", null).create();  
			    	   areaCheckListView = ad.getListView();  
			    	   ad.show();  
					break;
				default:
					break;
				}
			}
		});
	
		
		 bt1 = (Button)findViewById(R.id.button1);
			bt1.setOnClickListener(new OnClickListener() {  
		        @Override  
		        public void onClick(View v) {  
		          // myTextView.setText("ImageButton的监听事件");  
		        	Intent i2=new Intent(Settings.this, OpenClose.class);
					i2.putExtra("user",0);
					IPAD.flag=1;
					IPAD.stop=1;
					startActivity(i2); 
		            finish();
		        }  
		      }); 
			bt2 = (Button)findViewById(R.id.button2);
			bt2.setOnClickListener(new OnClickListener() {  
		        @Override  
		        public void onClick(View v) {  
		          // myTextView.setText("ImageButton的监听事件");  
		        	Intent i2=new Intent(Settings.this, DateManager.class);
					i2.putExtra("user",0);
					startActivity(i2); 
		            finish();
		        }  
		      }); 
			bt3 = (Button)findViewById(R.id.button3);
			bt3.setOnClickListener(new OnClickListener() {  
		        @Override  
		        public void onClick(View v) {  
		          // myTextView.setText("ImageButton的监听事件");  
		        	Intent i2=new Intent(Settings.this, Settings.class);
					i2.putExtra("user",0);
					startActivity(i2); 
		            finish();
		        }  
		      }); 
		
	}

	
	 private String showDialog_Layout(Context context,final String item_name) {  
	        LayoutInflater inflater = LayoutInflater.from(this);  
	        final View textEntryView = inflater.inflate(  
	                R.layout.dialoglayout, null);  
	        final EditText edtInput=(EditText)textEntryView.findViewById(R.id.edtInput);  
	        final AlertDialog.Builder builder = new AlertDialog.Builder(context);  
	        builder.setCancelable(false);  
	        builder.setIcon(R.drawable.icon);  
	        builder.setTitle(item_name);  
	        builder.setView(textEntryView);  
	        builder.setPositiveButton("确定 ",  
	                new DialogInterface.OnClickListener() {  
	                    @Override
						public void onClick(DialogInterface dialog, int whichButton) {  
	                    	if (item_name == "新密码")
	                        {
	                    		sendMessage("http://"+IPAD.ip+"/droidlogin/changepass.php", IPAD.user, edtInput.getText().toString());
	                          Toast.makeText(Settings.this, "修改成功!", 1).show();
	                        }
	                       
	                          if (item_name == "新组名"){
	                            
	                        	  sendMessage1( "http://"+IPAD.ip+"/droidlogin/newgroup.php", IPAD.user, edtInput.getText().toString());
	                          Toast.makeText(Settings.this, "添加成功!", 1).show();
	                          }
	                        }
	        		    	
	                     
	                });  
	        builder.setNegativeButton("取消",  
	                new DialogInterface.OnClickListener() {  
	                    @Override
						public void onClick(DialogInterface dialog, int whichButton) {  
	                    }  
	                });  
	        builder.show();  
	       
	        return edtInput.getText().toString();
	    }  

	 private static void send(String name){
	   	 try{
	    	     HttpClient httpclient = new DefaultHttpClient();
	    	     HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/deletedevice.php");                
	    	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    	//     System.out.println("服务器接收到：" +id+" "+IPAD.user+" "); 
	    	     postParameters.add(new BasicNameValuePair("usuario", IPAD.user));
	    	     postParameters.add(new BasicNameValuePair("name", name));
	 	         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
	 	         httpget.setEntity(formEntity);    
	 	         httpclient.execute(httpget); 
	 	         }catch(Exception e){
	 	        	// Log.e("log_tag", "Error in http connection"+e.toString());
	 	         }
	   	
	   }
	 
	 private void sendMessage(String URL, String username, String password)
	  {
	    try
	    {
	    	HttpClient httpclient = new DefaultHttpClient();
	    	HttpPost httpget = new HttpPost(URL);         
	    	List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    	postParameters.add(new BasicNameValuePair("usuario", username));
	    	postParameters.add(new BasicNameValuePair("newpass", password));
	    	UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
	    	httpget.setEntity(formEntity);    
	         httpclient.execute(httpget); 
	      return;
	    }
	    catch (Exception localException)
	    {
	      Log.e("log_tag", "Error in http connection" + localException.toString());
	    }
	  }

	 
	  private void sendMessage1(String URL, String oldgroup, String newgroup)
	  {
	    try
	    {
	    	  HttpClient httpclient = new DefaultHttpClient();
	    	     HttpPost httpget = new HttpPost(URL); 
	    	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    	     postParameters.add(new BasicNameValuePair("usuario", oldgroup + "_group"));
	    	     postParameters.add(new BasicNameValuePair("newGroup", newgroup));
	    	     UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
	 	         httpget.setEntity(formEntity);    
	 	         httpclient.execute(httpget); 
	      return;
	    }
	    catch (Exception localException)
	    {
	      Log.e("log_tag", "Error in http connection" + localException.toString());
	    }
	  }

	 
	private void FillListData()
	{
	  
	    list_GroupItem.add(new ListItem(0, getHashMap0("修改名称", "")));
	    list_GroupItem.add(new ListItem(0, getHashMap0("修改分组", "")));
	    list_GroupItem.add(new ListItem(0, getHashMap0("新建分组", "")));
	    list_GroupItem.add(new ListItem(0, getHashMap0("添加设备", "")));
	    list_GroupItem.add(new ListItem(0, getHashMap0("用电情况", "")));
	    list_GroupItem.add(new ListItem(0, getHashMap0("修改密码", "")));
	    list_GroupItem.add(new ListItem(0, getHashMap0("删除设备", "")));
	   
	 
	}


private HashMap<Integer, Object> getHashMap0(String s, String s1)
{

  
  HashMap<Integer, Object> map1 = new HashMap<Integer, Object>();
	map1.put(R.id.tv_time2, s);
	
	return map1;
}


private void getItem(String user) {
	 String result = null;
	 JSONArray jArray;
	 StringBuilder sb=null;
	 InputStream is = null;

try{
	 HttpClient httpclient = new DefaultHttpClient();
    HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/test233.php");                
    List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    postParameters.add(new BasicNameValuePair("usuario", user));
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
String ct_name;
try{	 
    jArray = new JSONArray(result);
    JSONObject json_data=null;
    areas = new String[jArray.length()];
    areaState =new boolean[jArray.length()];
    for(int i=0;i<jArray.length();i++){
  	  json_data = jArray.getJSONObject(i);
        ct_name=json_data.getString("name");
        areas[i] = ct_name;
        areaState[i]=false;
    }
 //  jishu = jArray.length();
}catch(JSONException e1){
	 Log.e("log_tag", "Error converting result "+e1.toString());
   //  Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
} catch (ParseException e1) {
    e1.printStackTrace();
}

}


	// �����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		MID = (int) info.id;

		switch (item.getItemId()) {
		case 0:
			// ��Ӳ���
			Toast.makeText(Settings.this,"���",Toast.LENGTH_SHORT).show();
			break;

		case 1:
			// ɾ�����
			break;

		case 2:
			// ɾ��ALL����
			break;

		default:
			break;
		}

		return super.onContextItemSelected(item);

	}

}