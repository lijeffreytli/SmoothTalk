package com.example.smoothtalk;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class GetContacts extends ActionBarActivity{
	
	public String contactNumber;
	public String contactName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, 0); //PICK_CONTACT SET TO 0?
	}
	
	//This method sends a text message to a specific phone number
	private void sendSMS(String phoneNumber, String message){
	       SmsManager sms = SmsManager.getDefault();
	       sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

	
	//Quick fix to the bug we were having. Allows user to return to main menu
	public void getContacts(View view) {
		// TODO Auto-generated method stub
		//Do something
		Intent intent = new Intent(this, GetContacts.class);
	    startActivity(intent);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Method obtains phone number from the contact Uri.
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (0) :
			if (resultCode == Activity.RESULT_OK) {
				System.out.println("TEST");
				Uri contactData = data.getData();
				Cursor c =  managedQuery(contactData, null, null, null, null);

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
						System.out.println("number is:"+ cNumber);
						contactNumber = cNumber;
					}
					contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				}
				sendSMS(contactNumber, "Hello!");
			}
		break;
		}
	}
	


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
}
