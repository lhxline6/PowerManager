package test.Droidlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import test.Droidlogin.R;













import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
/*��ӭ��Ļ*/
public class SetGroup extends Activity  {
	String user;
	TextView settitle, logoff;
	static final private int STATE_ID = Menu.FIRST;
    static final private int SETTINGS_ID = Menu.FIRST + 1;
    static final private int RENAME_ID = Menu.FIRST + 2;
    static final private int MANAGE_ID = Menu.FIRST + 3;

    JSONArray jArray;
    String result = null;
    InputStream is = null;
    StringBuilder sb=null;
    
    private ArrayList<ListItem> list_GroupItem;
    private ListView listView;
    private List<Map<String,String>> sNewsList;
//	private Context sContext;
	private Spinner sp;
	HashMap<String , String>  xiaobiao = new HashMap<String,String>(); 
	HashMap<String , Integer>  shuzi = new HashMap<String,Integer>();   
	private List<String> spanner_list = new ArrayList<String>();
	private String selected;
	 private TextView text;
	 private ArrayAdapter<String> adapter;  
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
		 	setContentView(R.layout.for_settings);
			sNewsList=new ArrayList<Map<String,String>>();
			
			text = (TextView) findViewById(R.id.title1);
			text.setText("修改分组");
		
			listView = (ListView)findViewById(R.id.listView2);
	        list_GroupItem = new ArrayList<ListItem>();
	        MyAdapter mAdapter_ListGroup = new MyAdapter(this, list_GroupItem);
			mAdapter_ListGroup.AddType(R.layout.list_item);
			mAdapter_ListGroup.AddType(R.layout.list_item1);
			listView.setAdapter(mAdapter_ListGroup);
			geneGroups(IPAD.user);
	 }
	 
	
		 
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
              sNewsList.clear();
              for(int i=0;i<jArray.length();i++){
                   json_data = jArray.getJSONObject(i);
                   ct_name=json_data.getString("socketgroup");
                   num=json_data.getInt("num");
                   if(num!=0){
                	   list_GroupItem.add(new ListItem(0, getHashMap0(ct_name," ")));
      				 	geneItems(user,ct_name,i);
      				 }
                   spanner_list.add(ct_name); 
              }
         }catch(JSONException e1){
         	 Log.e("log_tag", "Error converting result "+e1.toString());
              Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
         } catch (ParseException e1) {
              e1.printStackTrace();
         }
       }
 
	 private void geneItems(String user,String group,int num) {
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
         
          for(int i=0;i<jArray.length();i++){
        	  json_data = jArray.getJSONObject(i);
              ct_name=json_data.getString("name");
              list_GroupItem.add(new ListItem(1, getHashMap1(ct_name)));
            //  Toast.makeText(sContext,group, Toast.LENGTH_LONG).show();
              xiaobiao.put(ct_name,group);
              shuzi.put(ct_name,num);
          }
          
     }catch(JSONException e1){
     	 Log.e("log_tag", "Error converting result "+e1.toString());
           Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
     } catch (ParseException e1) {
          e1.printStackTrace();
     }

	 }
	 
	  private HashMap<Integer, Object> getHashMap0(String s1,String s2) {	
			HashMap<Integer, Object> map1 = new HashMap<Integer, Object>();
			map1.put(R.id.tv_time1, s1);
			
			return map1;
		}
	    
	    private HashMap<Integer, Object> getHashMap1(String s) {	
			HashMap<Integer, Object> map1 = new HashMap<Integer, Object>();
			map1.put(R.id.textView, s);
			map1.put(R.id.item_id, "   ");
			return map1;
		}

	
		 public void btnClick(View v){
		    	LinearLayout layout=(LinearLayout)v.getParent();
		    	String text1=((TextView)layout.findViewById(R.id.textView)).getText().toString();
		    		showDialog_Layout(SetGroup.this,text1);		
		    }
		
		 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
		        super.onCreateOptionsMenu(menu);
		        menu.add(0, STATE_ID, 0, R.string.state).setShortcut('0', 'b');
		        menu.add(0, SETTINGS_ID, 0, R.string.settings).setShortcut('1', 'c');
		        menu.add(0, MANAGE_ID, 0, R.string.manage).setShortcut('2', 'd');

		        return true;
		    }
		
		 @Override
		    public boolean onOptionsItemSelected(MenuItem item) {
		        switch (item.getItemId()) {
		        case STATE_ID:
		        	
		            return true;
		        case SETTINGS_ID:  
		        	//Intent i1=new Intent(ReName.this, SetPage.class);
				//	i1.putExtra("user",user);
					//startActivity(i1); 
		            finish();
		            return true;
		        case RENAME_ID:        
		            return true;
		        case MANAGE_ID:
		        	Intent i2=new Intent(SetGroup.this, Arrangement.class);
					i2.putExtra("user",user);
					startActivity(i2); 
		            finish();
		            return true;
		        }

		        return super.onOptionsItemSelected(item);
		    }
		 
		 
		 
		 private void showDialog_Layout(Context context,final String item_name) {  
		        LayoutInflater inflater = LayoutInflater.from(this);  
		        final View textEntryView = inflater.inflate(  
		                R.layout.dialoglayout1, null);  
		        final AlertDialog.Builder builder = new AlertDialog.Builder(context); 
		        builder.setCancelable(false);  
		        builder.setIcon(R.drawable.icon);  
		        builder.setTitle("选择新分组");  
		        builder.setView(textEntryView);  

		        sp=(Spinner)textEntryView.findViewById(R.id.spinner1);  
		        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spanner_list);
			
			
		        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        sp.setAdapter(adapter);
		        sp.setSelection(shuzi.get(item_name),true);

		        sp.setOnItemSelectedListener(
		                new OnItemSelectedListener() {
		                    @Override
							public void onItemSelected(
		                            AdapterView<?> parent, View view, int position, long id) {
		                    	 selected = parent.getItemAtPosition(position).toString();  
		                    }

		                    @Override
							public void onNothingSelected(AdapterView<?> parent) {
		                    }
		                });

		        builder.setPositiveButton("确定",  
		                new DialogInterface.OnClickListener() {  
		                    @Override
							public void onClick(DialogInterface dialog, int whichButton) {  
		                    	if(xiaobiao.get(item_name).equals(selected)){
		                    		finish();
		                    	}
		                       sendMessage("http://"+IPAD.ip+"/droidlogin/setgroup.php",IPAD.user,xiaobiao.get(item_name),selected,item_name);
		        		    	Intent i1=new Intent(SetGroup.this, SetGroup.class);
		        				startActivity(i1); 
		        	            finish();
		                    }  
		                });  
		        builder.setNegativeButton("取消",  
		                new DialogInterface.OnClickListener() {  
		                    @Override
							public void onClick(DialogInterface dialog, int whichButton) {  
		                    }  
		                });  
		        builder.show();  
		    }  
		 
		
	   
		 private void sendMessage(String URL,String user,String originGroup,String newGroup,String name){
		   	 try{
		    	     HttpClient httpclient = new DefaultHttpClient();
		    	     HttpPost httpget = new HttpPost(URL);                
		    	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		    	     postParameters.add(new BasicNameValuePair("usuario", user));
		 	         postParameters.add(new BasicNameValuePair("originName", originGroup));
		 	        
		 	         postParameters.add(new BasicNameValuePair("newGroup", newGroup));
		 	         
		 	         postParameters.add(new BasicNameValuePair("name", name));
		 	         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters,"UTF-8");
		 	         httpget.setEntity(formEntity);    
		 	         httpclient.execute(httpget); 
		 	         }catch(Exception e){
		 	        	 Log.e("log_tag", "Error in http connection"+e.toString());
		 	         }
		   	
		   }
		 
		 
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


