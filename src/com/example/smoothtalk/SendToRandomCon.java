package com.example.smoothtalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;

//Class that sends a random pick-up line to a random contact
public class SendToRandomCon extends Activity {

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
		
		boolean found = false;
		message = getPickUpLine();
		
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
		finish(); //return back to the main activity

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
