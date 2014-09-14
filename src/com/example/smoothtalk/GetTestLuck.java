package com.example.smoothtalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GetTestLuck extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_test_luck);
		
		// Remove the name of the app from action bar
		getActionBar().setDisplayShowTitleEnabled(false);
		
		TextView txtAre = (TextView) findViewById(R.id.textAre);
		Typeface fontAre = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtAre.setTypeface(fontAre);
		
		TextView txtYou = (TextView) findViewById(R.id.textYou);
		Typeface fontYou = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtYou.setTypeface(fontYou);
		
		TextView txtSure = (TextView) findViewById(R.id.textSure);
		Typeface fontSure = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtSure.setTypeface(fontSure);
		
		TextView txtWarning = (TextView) findViewById(R.id.textWarning);
		Typeface fontWarning = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtWarning.setTypeface(fontWarning);
		
		TextView txtButtonYes = (TextView) findViewById(R.id.button_yes);
		Typeface fontButtonYes = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtButtonYes.setTypeface(fontButtonYes);
		
		TextView txtButtonNo = (TextView) findViewById(R.id.button_no);
		Typeface fontButtonNo = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtButtonNo.setTypeface(fontButtonNo);
		
		Button btnYes = (Button)findViewById(R.id.button_yes);
		
        /* Once the user hits the "Send" button */
        btnYes.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {   
            	/* AlertDialog box for user confirmation */
            	AlertDialog.Builder builder1 = new AlertDialog.Builder(GetTestLuck.this);
                builder1.setMessage("Are you sure?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	
                    	boolean found = false;
                		String message = getPickUpLine();
                		
                		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null); 
                		int count = cursor.getCount(); //get the total number of contacts
                		Log.d("# of contacts", String.valueOf(count));
                		Random rnd = new Random();
                		
                		int index = rnd.nextInt(count); //obtain a random number
                		cursor.moveToPosition(index);
                		
                		int nameFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
                		String contact = cursor.getString(nameFieldColumnIndex);
                		Log.d("Contact name: ", contact);
                		
                		String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)); 
                		Log.d("contactId ", contactId);
                		
                		Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null); 
                		while (phones.moveToNext()) { 
                			String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));                 
                			Log.d("phoneNumber ", phoneNumber);
                		} 
                		phones.close(); 
                		sendSMS("15129653085", message);
                		finish();
                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });  
	}
	
	//Method that sends a pick-up line to a random contact
	public void sendToRandomCon(View view) {
		Intent intent = new Intent(this, SendToRandomCon.class);
	    startActivity(intent);
	}
	
	//Method that returns back to the main menu
	public void returnToMain(View view) {
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
	
	/* This method sends a text message to a specific phone number */
	private void sendSMS(String phoneNumber, String message){
		
		SmsManager sms = SmsManager.getDefault();
	    sms.sendTextMessage(phoneNumber, null, message, null, null);
	       
	}

	
	/* Read and Parse the text file */
	public String getPickUpLine(){
		//Read text from file
		Random rand = new Random();
		String pickupline = "";
		
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("Cute.txt")));
		    String line = "";
		    //Read the first line text file (Number of lines in text file)
		    String initialNumber = br.readLine();
		    int maxLineNumber = Integer.parseInt(initialNumber.replaceAll("[\\D]",""));
		    int randomNumber = rand.nextInt(maxLineNumber+1)+1;
		    int count = 0;
		    
		    while ((line = br.readLine()) != null && count != randomNumber) {
		        count++;
		    }
	        pickupline = line;
		}
		catch (IOException e) {
		    Toast.makeText(getBaseContext(), "Error: Unable to get pickup line", Toast.LENGTH_SHORT).show();
		    finish();
		}
		return pickupline;
	}	

}


