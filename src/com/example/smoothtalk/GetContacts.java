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
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.MenuItem;

public class GetContacts extends Activity {
	//Stores the indicated contact's phone number
	public String contactNumber; 
	//Stores the indicated contact's name (Currently not used)
	public String contactName;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_contacts);
		
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, 0); //PICK_CONTACT SET TO 0?
	}
	
	//This method sends a text message to a specific phone number
	private void sendSMS(String phoneNumber, String message){
	       SmsManager sms = SmsManager.getDefault();
	       sms.sendTextMessage(phoneNumber, null, message, null, null);
	}
	
	//Method obtains phone number from the contact Uri.
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (0) :
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c =  managedQuery(contactData, null, null, null, null);
				message = getPickUpLine();
				
				if (c.moveToFirst()) {
					String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
					String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
					if (hasPhone.equalsIgnoreCase("1")) {
						Cursor phones = getContentResolver().query( 
								ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
								ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
								null, null);
						phones.moveToFirst();
						String cNumber = phones.getString(phones.getColumnIndex("data1"));
						contactNumber = cNumber;
					}
					contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				}
				sendSMS(contactNumber, message);
			}
		break;
		}
		finish();
	}
	
	/* Read and Parse the text file */
	public String getPickUpLine(){
		//Read text from file
		Random rand = new Random();
		String pickupline = "";
		
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("Dirty.txt")));
		    String line;
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
		    //You'll need to add proper error handling here
		}
		return pickupline;
	}	
}
