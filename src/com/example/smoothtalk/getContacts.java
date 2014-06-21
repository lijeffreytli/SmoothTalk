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

public class getContacts extends ActionBarActivity{
	
	public String contactNumber;
	public String contactName;
	public String clickedContactId;
	

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
	
	private void sendSMS(String phoneNumber, String message){
	       SmsManager sms = SmsManager.getDefault();
	       sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

	
	//Quick fix to the bug we were having. Inception?
	public void getContacts(View view) {
		// TODO Auto-generated method stub
		//Do something
		Intent intent = new Intent(this, getContacts.class);
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
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
					String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
				    String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
				    System.out.println("contactId: " + contactId);
				    System.out.println("nameeeee " + name);
				    clickedContactId = contactId;
				}
				readContacts();
				sendSMS(contactNumber, "Hello!");
			}
		break;
		}
	}
	

	//This method reads from contact list and obtains specified contact phone number
    public void readContacts(){
    	boolean found = false;
         ContentResolver cr = getContentResolver();
         Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

         if (cur.getCount() > 0) {
            while (cur.moveToNext() && !found) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    System.out.println("name : " + name + ", ID : " + id);
                    System.out.println("CLICKED ID: " + clickedContactId + " AND id: " + id);
                    if (id == clickedContactId){
                    	found = true; 
                    	System.out.println("FOUND THEM");
                    }
                    contactName = name;
                    
                    // get the phone number
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                           ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                                           new String[]{id}, null);
                    while (pCur.moveToNext()) {
                          String phone = pCur.getString(
                                 pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                          System.out.println("phone" + phone);
                          contactNumber = phone;
                          
                    }
                    pCur.close();
                }
            }
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
