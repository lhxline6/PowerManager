

package test.Droidlogin;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Arrangement extends Activity
{
	 Button b2;
	String waittime;
	TextView atry;
	InputStream is = null;

	static final private int STATE_ID = Menu.FIRST;
    static final private int SETTINGS_ID = Menu.FIRST + 1;
    static final private int OPEN_ID = Menu.FIRST + 2;
    static final private int MANAGE_ID = Menu.FIRST + 3;
    String[] todo = new String [20];
    String s="";
    EditText edit ;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private ListView areaCheckListView;  
    private String[] areas;  
    private boolean[] areaState;  
    private int jishu=0;
    static final int BEGIN_DATE_DIALOG_ID = 0;
    static final int BEGIN_TIME_DIALOG_ID = 1;
    static final int END_DATE_DIALOG_ID = 2;
    static final int END_TIME_DIALOG_ID = 3;
    int start,end;
    Button choose,mankesure,cancel,BeginTime,BeginDate,EndTime,EndDate;
    @Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);
       
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        
        edit= (EditText) findViewById(R.id.editText1);
      	//text.setText("添加活动");
      		
        TextView text = (TextView) findViewById(R.id.titlefortime);
		text.setText("添加活动");
		//Toast.makeText(Arrangement.this,
       	//	   mHour+"   "+mMinute,
  		//		Toast.LENGTH_SHORT).show(); 
        geneItems(IPAD.user);
         BeginDate = (Button) findViewById(R.id.BeginDate);
        BeginDate.setText(mYear+"-"+mMonth+"-"+mDay);
        BeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
                showDialog(BEGIN_DATE_DIALOG_ID);
            }
        });

        
         BeginTime = (Button) findViewById(R.id.BeginTime);
        BeginTime.setText("00:00");
        BeginTime.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
                showDialog(BEGIN_TIME_DIALOG_ID);
            }
        });
        
        EndDate = (Button) findViewById(R.id.EndDate);
        EndDate.setText(mYear+"-"+mMonth+"-"+mDay);
        EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
                showDialog(END_DATE_DIALOG_ID);
            }
        });
        
         EndTime = (Button) findViewById(R.id.EndTime);
        EndTime.setText("00:00");
        EndTime.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
                showDialog(END_TIME_DIALOG_ID);
            }
        });
       String b = updateDisplay();
       
       
       choose = (Button) findViewById(R.id.choose1);
       choose.setOnClickListener(new CheckBoxClickListener());  
       
       mankesure = (Button)findViewById(R.id.button6);
       mankesure.setOnClickListener(new View.OnClickListener()
       {
    	 @Override
         public void onClick(View paramView)
         {
    		 mHour = c.get(Calendar.HOUR_OF_DAY);
    	     mMinute = c.get(Calendar.MINUTE);
    	     sendMessage("http://"+IPAD.ip+"/droidlogin/newdate.php", IPAD.user, BeginTime.getText().toString(), EndTime.getText().toString(),edit.getText().toString(),s);
             start =  Integer.valueOf(BeginTime.getText().toString().substring(0, 2))*3600+Integer.valueOf(BeginTime.getText().toString().substring(3))*60
  				 - (mHour*3600+mMinute*60);
             end = Integer.valueOf(EndTime.getText().toString().substring(0, 2))*3600+Integer.valueOf(EndTime.getText().toString().substring(3))*60
  				 - Integer.valueOf(BeginTime.getText().toString().substring(0, 2))*3600-Integer.valueOf(BeginTime.getText().toString().substring(3))*60;
             Toast.makeText(Arrangement.this, start+" "+end, Toast.LENGTH_SHORT).show();      
  		 if(start<0) {
  			 start+=86400;
  			 end+=86400;
  		 }
  		// Toast.makeText(Arrangement.this, start+"　"+end, Toast.LENGTH_LONG).show(); 
           new Thread( new Runnable() {     
        	    public void run() {     
        	    	try
        	        {	
        	    		  Socket socket =new Socket("192.168.1.100",8899);
		    	            //2.�õ�socket��д��
		    	            
		    	            //3.����������һ���Ĳ�������socket���ж�д����
		    	            Thread.sleep(start*1000);
		    	            OutputStream os=socket.getOutputStream();
		    	            PrintWriter pw=new PrintWriter(os);
		    	            //������
		    	          //  InputStream is=socket.getInputStream();
		    	        //    BufferedReader br=new BufferedReader(new InputStreamReader(is));
		    	            sendMessage1("http://"+IPAD.ip+"/droidlogin/time.php", IPAD.user, todo[0],todo[1], todo[2], "1","0");
		    	            pw.write("b", 0, 1);
		    	            pw.flush();

        	       			Thread.sleep(end*1000);
        	       			sendMessage1("http://"+IPAD.ip+"/droidlogin/time.php", IPAD.user, todo[0],todo[1], todo[2], "0", "0");
		    	               
        	       			OutputStream os1=socket.getOutputStream();
		    	            PrintWriter pw1=new PrintWriter(os1);
		    	            pw1.write("a", 0, 1);
		    	            pw1.flush();
		    	               return;
        	        }
        	        catch (Exception localException)
        	        {
        	          Log.e("log_tag", "Error in http connection" + localException.toString());
        	     }  
        	    	}          
        	}).start();
           
      /*     new Thread( new Runnable() {     
       	    public void run() {     
       	    	try
       	        {	
       	    		
       	    		  Socket socket =new Socket("192.168.1.108",8899);
		    	            //2.�õ�socket��д��
		    	            OutputStream os=socket.getOutputStream();
		    	            PrintWriter pw=new PrintWriter(os);
		    	            //������
		    	          //  InputStream is=socket.getInputStream();
		    	        //    BufferedReader br=new BufferedReader(new InputStreamReader(is));
		    	            //3.����������һ���Ĳ�������socket���ж�д����
		    	            Thread.sleep(50*1000);
		    	              pw.write("b", 0, 1);
		    	               pw.flush();
       	   //     sendMessage1("http://"+IPAD.ip+"/droidlogin/time.php", IPAD.user, todo[0],todo[1], todo[2], "1", String.valueOf(start));
       	   //     sendMessage1("http://"+IPAD.ip+"/droidlogin/time.php", IPAD.user, todo[0],todo[1], todo[2], "0", String.valueOf(end));
		    	               Thread.sleep(3*60*1000);
		    	               OutputStream os1=socket.getOutputStream();
			    	            PrintWriter pw1=new PrintWriter(os1);
		    	               pw1.write("a", 0, 1);
		    	               pw1.flush();
		    	              
		    	               return;
       	        }
       	        catch (Exception localException)
       	        {
       	          Log.e("log_tag", "Error in http connection" + localException.toString());
       	     }  
       	    	}          
       	}).start();
               */
               DateManager.instance.finish();
               Intent localIntent = new Intent(Arrangement.this, DateManager.class);
               localIntent.putExtra("user", 1);
               startActivity(localIntent);
               finish();
         }
       });
       cancel = ((Button)findViewById(R.id.button7));
       cancel.setOnClickListener(new View.OnClickListener()
       {
         public void onClick(View paramView)
         {
         finish();
         }
       });
    }
    
    
    class CheckBoxClickListener implements OnClickListener{  
    	  @Override  
    	  public void onClick(View v) {  
    	   AlertDialog ad = new AlertDialog.Builder(Arrangement.this)  
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
    	         s += areaCheckListView.getAdapter().getItem(i)+ ";"; 
    	         todo[i]=(String) areaCheckListView.getAdapter().getItem(i);
    	        }else{  
    	         areaCheckListView.getCheckedItemPositions().get(i,false);  
    	        }  
    	       }  
    	       if (areaCheckListView.getCheckedItemPositions().size() > 0){  
    	        choose.setText(s);
    	       }else{  
    	         //没有选择  
    	       }  
    	       dialog.dismiss();  
    	      }  
    	     }).setNegativeButton("取消", null).create();  
    	   areaCheckListView = ad.getListView();  
    	   ad.show();  
    	  }  
    	    }  

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case BEGIN_TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                		beginTimeSetListener, mHour, mMinute, false);
            case BEGIN_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                		beginDateSetListener,
                            mYear, mMonth, mDay);
            case END_TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                		endTimeSetListener, mHour, mMinute, false);
            case END_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                		endDateSetListener,
                            mYear, mMonth, mDay);
                
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case BEGIN_TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(mHour, mMinute);
                break;
            case BEGIN_DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
            case END_TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(mHour, mMinute);
                break;
            case END_DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;   
             
        }
    }    

    private String updateDisplay() {
        String a = 
            mMonth + 1+"-"+mDay+"-"+mYear+" "+pad(mHour)+":"+pad(mMinute);
        return a;
    }

 
            
            DatePickerDialog endDate;
            private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
                        int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    endDate.setTitle("截止日期");
                    EndDate.setText(mYear+"-"+mMonth+"-"+mDay);
                    
                }
            };
            
            DatePickerDialog beginDate;
            private DatePickerDialog.OnDateSetListener beginDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
                        int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    beginDate.setTitle("起始日期");
                    BeginDate.setText(mYear+"-"+mMonth+"-"+mDay);
                    
                }
            };
            TimePickerDialog endTime;
            private TimePickerDialog.OnTimeSetListener endTimeSetListener = 
            new TimePickerDialog.OnTimeSetListener() {

                @Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                   // endTime.setTitle("截止日期");
                    EndTime.setText(pad(mHour)+":"+pad(mMinute));
                 
                }
            };
            TimePickerDialog beginTime;
            private TimePickerDialog.OnTimeSetListener beginTimeSetListener = 
            new TimePickerDialog.OnTimeSetListener() {
                @Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                   // beginTime.setTitle("截止日期");
                   BeginTime.setText(pad(mHour)+":"+pad(mMinute));               
                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    
    private void geneItems(String user) {
		 String result = null;
		 JSONArray jArray;
		 StringBuilder sb=null;
		 InputStream is = null;

    try{
    	 HttpClient httpclient = new DefaultHttpClient();
         HttpPost httpget = new HttpPost("http://"+IPAD.ip+"/droidlogin/getitems.php");                
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
        jishu = jArray.length();
    }catch(JSONException e1){
    	 Log.e("log_tag", "Error converting result "+e1.toString());
          Toast.makeText(getBaseContext(), "No id Found" ,Toast.LENGTH_LONG).show();
    } catch (ParseException e1) {
         e1.printStackTrace();
    }

	 }
    
    
    private void sendMessage(String URL,String user,String start,String end,String content,String name){
    	 try{
     	     HttpClient httpclient = new DefaultHttpClient();
     	     HttpPost httpget = new HttpPost(URL);                
     	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        	 postParameters.add(new BasicNameValuePair("usuario", user));
  	         postParameters.add(new BasicNameValuePair("start", start));
  	         postParameters.add(new BasicNameValuePair("end", end));
  	         postParameters.add(new BasicNameValuePair("content", content));
  	         postParameters.add(new BasicNameValuePair("name", name));
  	 
  	         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters ,"UTF-8");
  	         httpget.setEntity(formEntity);    
  	         httpclient.execute(httpget); 
  	         }catch(Exception e){
  	        	 Log.e("log_tag", "Error in http connection"+e.toString());
  	         }
    }
    
    private void sendMessage1(String URL, String username, String name0, String name1, String name2, String state, String time)
    {
      try
      {
        if (time == "NULL")
          Toast.makeText(getBaseContext(), "时间未设置！", 1).show();
        HttpClient httpclient = new DefaultHttpClient();
	     HttpPost httpget = new HttpPost(URL);                
	     List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	     postParameters.add(new BasicNameValuePair("usuario", username));
	     postParameters.add(new BasicNameValuePair("name0", name0));
        postParameters.add(new BasicNameValuePair("name1", name1));
        postParameters.add(new BasicNameValuePair("name2", name2));
        postParameters.add(new BasicNameValuePair("state", state));
        postParameters.add(new BasicNameValuePair("waittime", time));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters ,"UTF-8");
        httpget.setEntity(formEntity); 
        httpclient.execute(httpget);
        return;
      }
      catch (Exception localException)
      {
        Log.e("log_tag", "Error in http connection" + localException.toString());
      }
    }
    
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	        super.onCreateOptionsMenu(menu);
	        menu.add(0, STATE_ID, 0, R.string.state).setShortcut('0', 'b');
	        menu.add(0, SETTINGS_ID, 0, R.string.settings).setShortcut('1', 'c');
	        menu.add(0, OPEN_ID, 0, R.string.open).setShortcut('2', 'd');
	        menu.add(0, MANAGE_ID, 0, R.string.manage).setShortcut('3', 'e');
	        return true;
	    }

	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case STATE_ID:
	        	
	            return true;
	        case SETTINGS_ID:
	        	//Intent i1=new Intent(Arrangement.this, SetPage.class);
			//	i1.putExtra("user",user);
			//	startActivity(i1); 
				finish();
	            return true;
	        case OPEN_ID:
	        	Intent i2=new Intent(Arrangement.this, ReName.class);
				
				startActivity(i2); 
				finish();
	            return true;
	        case MANAGE_ID:
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
    
}

