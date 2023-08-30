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

public class Volunteers_view_food_availability extends Activity implements JsonResponse, OnItemClickListener {
	ListView l1;
	String[] provider_id,provider,lati,longi,date,food_status,details;
	public static String p_id,p_type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteers_view_food_availability);
		
		l1=(ListView)findViewById(R.id.lvavailability);
		
		l1.setOnItemClickListener(this);
		
		JsonReq JR= new JsonReq();
		JR.json_response=(JsonResponse)Volunteers_view_food_availability.this;
		String q="volunteers_view_food/";
		JR.execute(q);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volunteers_view_food_availability, menu);
		return true;
	}

	@Override
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
	
				try{
					String status=jo.getString("status");
					if(status.equalsIgnoreCase("success"))
					{
						JSONArray ja=(JSONArray)jo.getJSONArray("data");
						provider_id = new String[ja.length()];
						provider = new String[ja.length()];
						lati= new String[ja.length()];
						longi= new String[ja.length()];
						date= new String[ja.length()];
						food_status= new String[ja.length()];
						details = new String[ja.length()];
						
						
						
						for(int i=0;i<ja.length();i++)
						{
							provider_id[i]=ja.getJSONObject(i).getString("provider_id");
							provider[i]=ja.getJSONObject(i).getString("provider_type");
							lati[i]=ja.getJSONObject(i).getString("latitude");
							longi[i]=ja.getJSONObject(i).getString("longitude");
							date[i]=ja.getJSONObject(i).getString("date_time");
							food_status[i]=ja.getJSONObject(i).getString("status");
							
							details[i]="provider : "+provider[i]+"\nlati : "+lati[i]+"\nlongi : "+longi[i]+"\ndate : "+date[i]+"\nfood_status : "+food_status[i];
							
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		p_id = provider_id[arg2];
		p_type = provider[arg2];
		
final CharSequence[] items = {"Food Distribution","Cancel"};
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(Volunteers_view_food_availability.this);
		builder.setTitle("Select Option!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int item) {
				// TODO Auto-generated method stub
				if (items[item].equals("Food Distribution"))
				{
					startActivity(new Intent(getApplicationContext(),Volunteers_distribute_food.class));
				}

				 else if (items[item].equals("Cancel")) {
	                    dialog.dismiss();
	                }
			}
		});
		builder.show();
		
	}
	}
		
