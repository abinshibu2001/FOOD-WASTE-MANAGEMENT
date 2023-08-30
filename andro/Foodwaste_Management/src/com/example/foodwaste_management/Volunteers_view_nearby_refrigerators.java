package com.example.foodwaste_management;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Volunteers_view_nearby_refrigerators extends Activity implements JsonResponse{
	
	ListView l1;
	String[] lati,longi,food_status,details;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteers_view_nearby_refrigerators);
		
		l1=(ListView)findViewById(R.id.lvrefrigerators);
		

		JsonReq JR= new JsonReq();
		JR.json_response=(JsonResponse)Volunteers_view_nearby_refrigerators.this;
		String q="volunteers_view_nearby_refrigerators/?lati="+LocationService.lati+"&longi="+LocationService.logi;
		JR.execute(q);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volunteers_view_nearby_refrigerators, menu);
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
						lati= new String[ja.length()];
						longi= new String[ja.length()];
						food_status= new String[ja.length()];
						details=new String[ja.length()];
						
						
						
						for(int i=0;i<ja.length();i++)
						{
							
							lati[i]=ja.getJSONObject(i).getString("nearbylati");
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
		
	}

