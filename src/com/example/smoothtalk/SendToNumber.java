package com.example.smoothtalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendToNumber extends ActionBarActivity {
	Button btnSendSMS;
    EditText txtPhoneNo;
    EditText txtMessage;
    /* Stores the pick-up line that's sent to the contact */
    private String message;
	
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_to_number);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
 
        /* Once the user hits the "Send" button */
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {   
            	/* AlertDialog box for user confirmation */
            	AlertDialog.Builder builder1 = new AlertDialog.Builder(SendToNumber.this);
                builder1.setMessage("Send to this number?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	
                    	message = getPickUpLine();
                    	String phoneNo = txtPhoneNo.getText().toString(); 
                    	
                    	if (phoneNo.length()>0) { //Checks whether the number is not null      
                        	sendSMS(phoneNo, message); 
                            finish(); //After sending the message, return back to MainActivity
                        } else //Throw an exception if the number is invalid
                            Toast.makeText(getBaseContext(), 
                                "Please enter a valid phone number.", 
                                Toast.LENGTH_SHORT).show();
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

	/* This method sends a text message to a specific phone number */
		private void sendSMS(String phoneNumber, String message){
		       SmsManager sms = SmsManager.getDefault();
		       sms.sendTextMessage(phoneNumber, null, message, null, null);
		}
	
	/* Creates the actionbar menu (Currently Unused) */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_to_number, menu);
		return true;
	}

	/* Removing ActionBar functionality */
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_send_to_number,
					container, false);
			return rootView;
		}
	}
	
	/* Read and Parse the text file */
	public String getPickUpLine(){
		//Read text from file
		Random rand = new Random();
		String pickupline = "";
		
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("Dirty.txt")));
		    String line = "";
		    //Read the first line text file (Number of lines in text file)
		    String initialNumber = br.readLine();
		    //parse the string to an int via regex
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
