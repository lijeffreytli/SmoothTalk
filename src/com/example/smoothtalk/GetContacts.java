package com.example.smoothtalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

public class GetContacts extends ActionBarActivity {
	/* Stores the indicated contact's phone number */
	public String contactNumber; 
	/* Stores the indicated contact's name (Currently not used) */
	public String contactName;
	/* Stores the pick-up line that's sent to the contact */
	private String message;
	/* Request code for contracts */
	private static final int CONTACT_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_contacts);
		
		Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
		intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
		startActivityForResult(intent, CONTACT_CODE);
	}
	
	/* This method sends a text message to a specific phone number */
	private void sendSMS(String phoneNumber, String message){
		
		SmsManager sms = SmsManager.getDefault();
	    sms.sendTextMessage(phoneNumber, null, message, null, null);
	       
	}
	
	/* Method obtains phone number from the contact Uri. */
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (CONTACT_CODE) :
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c =  managedQuery(contactData, null, null, null, null);
				message = getPickUpLine();
				
				if (c.moveToFirst()) {
					String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
					String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
					if (hasPhone.equalsIgnoreCase("1")) {
//						Cursor phones = getContentResolver().query( 
//								ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
//								ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
//								null, null);
						Cursor phones = getContentResolver().query(contactData, null, null, null, null);
						if(phones.moveToFirst()){
	//						String cNumber = phones.getString(phones.getColumnIndex("data1"));
							String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
							contactNumber = cNumber;
						}
						//else?
					}
					contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				}
				sendSMS(contactNumber, message);
			}
		break;
		}
		finish(); //After sending the message, return back to MainActivity
	}
	
	/* Read and Parse the text file */
	public String getPickUpLine(){
		//Read text from file
		Random rand = new Random();
		String pickupline = "";
		
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("Cheesy.txt")));
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
