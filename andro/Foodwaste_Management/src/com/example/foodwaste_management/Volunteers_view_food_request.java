package com.example.foodwaste_management;


import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Volunteers_view_food_request extends Activity implements JsonResponse{
	ListView l1;
	String[] charity,date,quantity,rq_status,details;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteers_view_food_request);
		
		l1=(ListView)findViewById(R.id.lvrequest);
		
		JsonReq JR= new JsonReq();
		JR.json_response=(JsonResponse)Volunteers_view_food_request.this;
		String q="volunteers_view_request/";
		JR.execute(q);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volunteers_view_food_request, menu);
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
						charity=new String[ja.length()];
						date= new String[ja.length()];
						quantity= new String[ja.length()];
						rq_status= new String[ja.length()];
						details=new String[ja.length()];
						
						
						
						for(int i=0;i<ja.length();i++)
						{
							charity[i]=ja.getJSONObject(i).getString("charity_name");
							date[i]=ja.getJSONObject(i).getString("date_time");
							quantity[i]=ja.getJSONObject(i).getString("quantity_required");
							rq_status[i]=ja.getJSONObject(i).getString("request_status");
							details[i]="charity : "+charity[i]+"\nDate : "+date[i]+"\nquantity : "+quantity[i]+"\nrq_status : "+rq_status[i];
							
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

