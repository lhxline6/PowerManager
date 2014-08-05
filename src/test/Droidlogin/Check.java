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
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Check extends Activity {

	SimpleAdapter adapter;
	public int MID;

	Canvas canvas;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  public static Check instance = null;
	  int[] bottom;
	  String[] contents;
	  DrawView formas;
	  int[] jishu;
	  @SuppressWarnings("deprecation")
	  private AbsoluteLayout layout;
	  Timer timer;
	  int[] top;
	  int total = 1;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		layout=(AbsoluteLayout)findViewById(R.id.ab);
		TextView text = (TextView) findViewById(R.id.title);
		text.setText("用电情况");
		geneItems("aaa");
	   	instance = this;
	}


private static String pad(int c) {
    if (c >= 10)
        return String.valueOf(c);
    else
        return "0" + String.valueOf(c);
}



private void geneItems(String id)
{
  
  StringBuilder sb=null;
	 InputStream is = null;
	 String result = null;
	 JSONArray jArray;
  try
  {
  
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/draw.php");                
    List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    postParameters.add(new BasicNameValuePair("id", id));
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
   
       
     
        String endtime,starthour,startmin,endhour,endmin,content,name;
     //   int j;
        int k;
        int i2;
        int total=1;
        try
        {
        	jArray = new JSONArray(result);
           JSONObject json_data=null;
           /// top = new int[jArray.length()];
           // bottom = new int[jArray.length()];
            jishu = new int[jArray.length()];
            //contents = new String[jArray.length()];
            for(int i=0;i<jArray.length();i++){
            	json_data = jArray.getJSONObject(i);
            
            	jishu[i] = json_data.getInt("datas");
              
            }
            
         //   for(int i=1;i<total+1;i++){
            	for(int j=0;j<jArray.length()-1;j++){
            		
            		MyView formas = new MyView(this);
            		formas.setMinimumHeight(500);
                    formas.setMinimumWidth(300);
                    formas.p1x = (j+1)*10+20;
                    formas.p1y = (int) (270-3*jishu[j]);
                    formas.p2x = (j+2)*10+20;
                    formas.p2y= (int) (270-3*jishu[j+1]);
                    formas.invalidate();
                 
                   layout.addView(formas);
            		}
            		
            	
            	
          //  }
    
         
    }catch(JSONException e1){
    	 Log.e("log_tag", "Error converting result "+e1.toString());
         Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
    } catch (ParseException e1) {
         e1.printStackTrace();
    }
  }

	
}

