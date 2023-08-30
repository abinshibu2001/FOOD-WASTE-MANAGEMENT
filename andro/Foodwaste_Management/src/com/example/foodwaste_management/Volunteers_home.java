package com.example.foodwaste_management;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Volunteers_home extends Activity {
	
	Button b1,b2,b3,b5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteers_home);
		
		b1=(Button)findViewById(R.id.btrequest);
		b2=(Button)findViewById(R.id.btavailability);
		b3=(Button)findViewById(R.id.btrefrigerators);
		b5=(Button)findViewById(R.id.btlogout);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(getApplicationContext(),Volunteers_view_food_request.class));
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(getApplicationContext(),Volunteers_view_food_availability.class));
			}
		});
		
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(getApplicationContext(),Volunteers_view_nearby_refrigerators.class));
			}
		});
		
//		b4.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				
//				startActivity(new Intent(getApplicationContext(),Volunteers_distribute_food.class));
//			}
//		});
		
		b5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(getApplicationContext(),Login.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volunteers_home, menu);
		return true;
	}

}
