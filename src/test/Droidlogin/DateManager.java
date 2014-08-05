package test.Droidlogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

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

import test.Droidlogin.DateManager;
import test.Droidlogin.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DateManager extends Activity {

	SimpleAdapter adapter;
	public int MID;

	Canvas canvas;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	String[] todo = new String [20];
    String s="";
	 private static final int EXIT_ID = 2;
	  private static final int SETTINGS_ID = 1;
	  public static DateManager instance = null;
	  int[] bottom;
	  String[] contents;
	  DrawView formas;
	  int[] jishu;
	  @SuppressWarnings("deprecation")
	  private AbsoluteLayout layout;
	  private ListView listView;
	  private ArrayList<ListItem> list_GroupItem;
	  Timer timer;
	  int[] top;
	  int total = 1;
	  private ListView areaCheckListView;  
	    private String[] areas;  
	    private boolean[] areaState;  
	    Button bt1,bt2,bt3;
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rili);
	//	setContentView(new SampleView(this));
		//	sListView = (XListView) findViewById(R.id.xListView);
		//	FillListData();
			//sListView.setOnItemClickListener(new OnItemClickListener() {

		//	@Override
		//	public void onItemClick(AdapterView<?> arg0, View arg1,
		//			int position, long id) {
			
		//		Toast.makeText(DateManager.this,"选中时间" + list.get(position).get("name").toString(),
		//				Toast.LENGTH_SHORT).show();
		//	}
		//});
		

		listView = (ListView)findViewById(R.id.listView1);
		listView.setDivider(null);
        list_GroupItem = new ArrayList<ListItem>();
          
       
        
        MyAdapter mAdapter_ListGroup = new MyAdapter(this, list_GroupItem);
		mAdapter_ListGroup.AddType(R.layout.list_item7);
	//	mAdapter_ListGroup.AddType(R.layout.list_item5); //toggle 开
		listView.setAdapter(mAdapter_ListGroup);
	    FillListData();
	    instance = this;
	    getItem(IPAD.user);
	    ItemOnLongClick1();
	    
	   
	    layout=(AbsoluteLayout)findViewById(R.id.ably);
	    geneItems(IPAD.user);
	    
	    
	   bt1 = (Button)findViewById(R.id.button1);
	   bt1.setOnClickListener(new OnClickListener() {  
	        @Override  
	        public void onClick(View v) {  
	          // myTextView.setText("ImageButton的监听事件");  
	        	Intent i2=new Intent(DateManager.this, OpenClose.class);
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
	        	Intent i2=new Intent(DateManager.this, DateManager.class);
				i2.putExtra("user",0);
				startActivity(i2); 
	            finish();
	        }  
	      }); 
		bt3 = (Button)findViewById(R.id.button3);
		bt3.setOnClickListener(new OnClickListener() {  
	        @Override  
	        public void onClick(View v) {  
	        	Intent i2=new Intent(DateManager.this, Settings.class);
				i2.putExtra("user",0);
				
				startActivity(i2); 
	            finish();
	        }  
	      }); 
	}
	
private void FillListData()
{
  for (int i = 0; ; ++i)
  {
    if (i >= 24)
      return;
    this.list_GroupItem.add(new ListItem(0, getHashMap0(String.valueOf(i) + ":00 - " + String.valueOf(i + 1) + ":00", "")));
  }
}

private static String pad(int c) {
    if (c >= 10)
        return String.valueOf(c);
    else
        return "0" + String.valueOf(c);
}

private HashMap<Integer, Object> getHashMap0(String s, String s1)
{

  
  HashMap<Integer, Object> map1 = new HashMap<Integer, Object>();
	map1.put(R.id.tv, s);
	return map1;
}

private void geneItems(String username)
{
  
  StringBuilder sb=null;
	 InputStream is = null;
	 String result = null;
	 JSONArray jArray;
  try
  {
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/getdate.php");                
    List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    postParameters.add(new BasicNameValuePair("usuario", username));
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
        String starttime,endtime,starthour,startmin,endhour,endmin,content,name;
     //   int j;
        int k;
        int i2;
        int total=1;
        try
        {
        	jArray = new JSONArray(result);
            JSONObject json_data=null;
            top = new int[jArray.length()];
            bottom = new int[jArray.length()];
            jishu = new int[jArray.length()];
            contents = new String[jArray.length()];
            for(int i=0;i<jArray.length();i++){
            	json_data = jArray.getJSONObject(i);
            	  starttime = json_data.getString("start");
                  endtime = json_data.getString("end");
                  content = json_data.getString("content");
                 name = json_data.getString("name");
                 
                 starthour = starttime.substring(0, 2);
                 startmin = starttime.substring(3);
                 endhour = endtime.substring(0, 2);
                 endmin = endtime.substring(3);
                 jishu[i]=1;
                 contents[i] = (content + " : " + name);
                 top[i] = (816 * (60 * Integer.valueOf(starthour).intValue() + Integer.valueOf(startmin).intValue()) / 1440);
                 bottom[i] = (816 * (60 * Integer.valueOf(endhour).intValue() + Integer.valueOf(endmin).intValue()) / 1440);
                 for(int j=0;j<i;j++){
                	 if ((top[j] >= this.bottom[i]) || (bottom[j] <= this.top[i])){ 
                	 }
                	 else{
                		 if(jishu[i]==jishu[j]){
                			 jishu[i]=jishu[j]+1;
                		 }
                		 if(total<jishu[i])
                			 total = jishu[i];
                	 } 
                 } 
            }
            
            for(int i=1;i<total+1;i++){
            	for(int j=0;j<jArray.length();j++){
            		if(jishu[j]==i){
            			 DrawView formas = new DrawView(this);           	         
        	            formas.setMinimumHeight(865);
        	            formas.setMinimumWidth(300);
        	            formas.left = (320 * (i - 1) / total);
        	            formas.right = (i * 320 / total);
        	            formas.top = top[j];
        	            formas.bottom =bottom[j];
        	            int d = (int)(Math.random() * 6);
        	            formas.i = d;
        	            formas.invalidate();
        	            formas.neirong = this.contents[j];
            	           layout.addView(formas);
            		}
            		
            	}
            	
            }
    
         
    }catch(JSONException e1){
    	 Log.e("log_tag", "Error converting result "+e1.toString());
         Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
    } catch (ParseException e1) {
         e1.printStackTrace();
    }
  }

	private void ItemOnLongClick1() {

		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.add(0, 0, 0, "新建日程");
						menu.add(0, 1, 0, "删除日程");
						menu.add(0, 2, 0, "取消");
					}
				});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		MID = (int) info.id;
		switch (item.getItemId()) {
		case 0:
			Intent i1=new Intent(DateManager.this, Arrangement.class);
		//	i1.putExtra("user",user);
		
			startActivity(i1); 
			break;
		case 1:
			  
	    	   AlertDialog ad = new AlertDialog.Builder(DateManager.this)  
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
	    	        	sendMessage( areaCheckListView.getAdapter().getItem(i).toString());
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

		return super.onContextItemSelected(item);

	}
	
	private static void sendMessage(String name){
	   	 try{
	    	     HttpClient httpclient = new DefaultHttpClient();
	    	     HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/deletedate.php");                
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
	 private void getItem(String user) {
		 String result = null;
		 JSONArray jArray;
		 StringBuilder sb=null;
		 InputStream is = null;

    try{
    	 HttpClient httpclient = new DefaultHttpClient();
         HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/getdates.php");                
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
	
}

