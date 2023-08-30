package com.example.foodwaste_management;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Volunteers_distribute_food extends Activity implements JsonResponse, OnItemSelectedListener {
	Spinner s1;
	Button b1;
	String[] charity_id,charity_name,details;
	SharedPreferences sh;
	public static String c_id;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteers_distribute_food);
		
		s1=(Spinner)findViewById(R.id.spcharity);
		b1=(Button)findViewById(R.id.btdistribute);
		sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		JsonReq JR= new JsonReq();
		JR.json_response=(JsonResponse)Volunteers_distribute_food.this;
		String q="volunteers_view_charity/";
		JR.execute(q);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				JsonReq JR= new JsonReq();
				JR.json_response=(JsonResponse)Volunteers_distribute_food.this;
				String q="volunteers_distribute_food/?logid="+sh.getString("logid", "")+"&p_id="+Volunteers_view_food_availability.p_id+"&p_type="+Volunteers_view_food_availability.p_type+"&c_id="+c_id;
				JR.execute(q);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volunteers_distribute_food, menu);
		return true;
	}

	@Override
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		
		try{
			String method=jo.getString("method");
			if(method.equalsIgnoreCase("volunteers_view_charity"))
			{
		try{
			String status=jo.getString("status");
			if(status.equalsIgnoreCase("success"))
			{
				JSONArray ja=(JSONArray)jo.getJSONArray("data");
				charity_id= new String[ja.length()];
				charity_name= new String[ja.length()];
				details=new String[ja.length()];
				
				
				
				for(int i=0;i<ja.length();i++)
				{
					charity_id[i]=ja.getJSONObject(i).getString("charity_id");
					charity_name[i]=ja.getJSONObject(i).getString("charity_name");
					details[i]=charity_name[i];
					
				}
				//driver_list.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.spin,ConfirmRide.name_s));
				ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,details);
				s1.setAdapter(ar);
				s1.setOnItemSelectedListener(this);
			
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
		if(method.equalsIgnoreCase("volunteers_distribute_food"))
		{
			try
			{
				String status=jo.getString("status");
				Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
				if(status.equalsIgnoreCase("success"))
				{
					Toast.makeText(getApplicationContext(), "Distribution Successfully", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getApplicationContext(),Volunteers_home.class));
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
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		c_id = charity_id[arg2];
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
