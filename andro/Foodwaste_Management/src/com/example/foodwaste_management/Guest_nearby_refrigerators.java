package com.example.foodwaste_management;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Guest_nearby_refrigerators extends Activity implements JsonResponse, OnItemClickListener {
	ListView l1;
	String[] ref_id,lati,longi,food_status,details;
	public static String refid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guest_nearby_refrigerators);
		
		l1=(ListView)findViewById(R.id.lvrefrigerators);
		
		l1.setOnItemClickListener(this);
		
		JsonReq JR= new JsonReq();
		JR.json_response=(JsonResponse)Guest_nearby_refrigerators.this;
		String q="guest_nearby_refrigerators/?lati="+LocationService.lati+"&longi="+LocationService.logi;
		JR.execute(q);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guest_nearby_refrigerators, menu);
		return true;
	}
	@Override
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		
		try{
			String method=jo.getString("method");
			if(method.equalsIgnoreCase("guest_nearby_refrigerators"))
			{
	
				try{
					String status=jo.getString("status");
					if(status.equalsIgnoreCase("success"))
					{
						JSONArray ja=(JSONArray)jo.getJSONArray("data");
						ref_id= new String[ja.length()];
						lati= new String[ja.length()];
						longi= new String[ja.length()];
						food_status= new String[ja.length()];
						details=new String[ja.length()];
						
						
						
						for(int i=0;i<ja.length();i++)
						{
							ref_id[i]=ja.getJSONObject(i).getString("ref_id");
							lati[i]=ja.getJSONObject(i).getString("latitude");
							longi[i]=ja.getJSONObject(i).getString("longitude");
							food_status[i]=ja.getJSONObject(i).getString("food_status");
							
							details[i]="lati : "+lati[i]+"\nlongi : "+longi[i]+"\nfood_status : "+food_status[i];
							
						}
						//driver_list.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.spin,ConfirmRide.name_s));
						l1.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,details));
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
					}
				}
				catch(Exception e){
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "haii"+e, Toast.LENGTH_LONG).show();
				}
			}
			if(method.equalsIgnoreCase("guest_put_food"))
			{
				try
				{
					String status=jo.getString("status");
					Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
					if(status.equalsIgnoreCase("success"))
					{
						Toast.makeText(getApplicationContext(), "Put Food Successfully", Toast.LENGTH_LONG).show();
						startActivity(new Intent(getApplicationContext(),Guest_home.class));
					}
					
					else
					{
						Toast.makeText(getApplicationContext(), "Not Successfull", Toast.LENGTH_LONG).show();
					}
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Hai"+e, Toast.LENGTH_LONG).show();
				}
			}
	
	}
		catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "haii"+e, Toast.LENGTH_LONG).show();
		}
			
		}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		refid = ref_id[arg2];
		
final CharSequence[] items = {"Put Food","Cancel"};
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(Guest_nearby_refrigerators.this);
		builder.setTitle("Select Option!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int item) {
				// TODO Auto-generated method stub
				if (items[item].equals("Put Food"))
				{
					JsonReq jr= new JsonReq();
					jr.json_response=(JsonResponse) Guest_nearby_refrigerators.this;
					String q="guest_put_food/?ref_id="+refid;
					q.replace(" ", "%20");
					jr.execute(q);
				}

				 else if (items[item].equals("Cancel")) {
	                    dialog.dismiss();
	                }
			}
		});
		builder.show();
		
	}
	}
		
