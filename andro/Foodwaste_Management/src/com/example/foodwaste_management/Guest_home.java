package com.example.foodwaste_management;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Guest_home extends Activity implements JsonResponse{
	Button b1,b2,b4;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guest_home);
		
		b1=(Button)findViewById(R.id.btlogin);
		b2=(Button)findViewById(R.id.btfood);
//		b3=(Button)findViewById(R.id.bthandover);
		b4=(Button)findViewById(R.id.btrefrigerators);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Login.class));
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				JsonReq jr= new JsonReq();
				jr.json_response=(JsonResponse) Guest_home.this;
				String q="guest_food/?lati="+LocationService.lati+"&longi="+LocationService.logi;
				q.replace(" ", "%20");
				jr.execute(q);
			}
		});
		
//		b3.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent(getApplicationContext(),Guest_handover_food.class));
//			}
//		});
		b4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Guest_nearby_refrigerators.class));
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guest_home, menu);
		return true;
	}
	@Override
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		
		try
		{
			String status=jo.getString("status");
			Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
			if(status.equalsIgnoreCase("success"))
			{
				Toast.makeText(getApplicationContext(), "Food Added Successfully", Toast.LENGTH_LONG).show();
//				startActivity(new Intent(getApplicationContext(),Login.class));
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

