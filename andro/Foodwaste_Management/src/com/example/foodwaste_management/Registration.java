package com.example.foodwaste_management;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registration extends Activity implements JsonResponse{
	EditText e1,e2,e3,e4,e5,e6,e7;
	Button b1;
	RadioButton r1,r2;
	String fname,lname,gender,age,phone,email,user,pass;
	String[] sid,sname;
	public static String sids;
//	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		
		e1=(EditText)findViewById(R.id.etfirst);
		e2=(EditText)findViewById(R.id.etlast);
		e3=(EditText)findViewById(R.id.etage);
		e4=(EditText)findViewById(R.id.etphone);
		e5=(EditText)findViewById(R.id.etemail);
		e6=(EditText)findViewById(R.id.etuser);
		e7=(EditText)findViewById(R.id.etpass);
		
		r1=(RadioButton)findViewById(R.id.rdmale);
		r2=(RadioButton)findViewById(R.id.rdfemale);
		
//		
		
		b1=(Button)findViewById(R.id.btregister);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				fname=e1.getText().toString();
				lname=e2.getText().toString();
				age=e3.getText().toString();
				phone=e4.getText().toString();
				email=e5.getText().toString();
				user=e6.getText().toString();
				pass=e7.getText().toString();
				
				if(r1.isChecked())
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}
				
				
//			
				JsonReq jr= new JsonReq();
				jr.json_response=(JsonResponse) Registration.this;
				String q="register/?firstname="+fname+"&lastname="+lname+"&age="+age+"&gender="+gender+"&phone="+phone+"&email="+email+"&user="+user+"&pass="+pass;
				q.replace(" ", "%20");
				jr.execute(q);
				
			}
		});
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
				Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
				startActivity(new Intent(getApplicationContext(),Login.class));
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

