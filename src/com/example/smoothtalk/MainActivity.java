package com.example.smoothtalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	public final static String PHONE_NUMBER = "com.example.smoothtalk.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public void getContacts(View view) {
		// TODO Auto-generated method stub
		//Do something
		Intent intent = new Intent(this, GetContacts.class);
	    startActivity(intent);
	}
	
	
	public void getSettings(View view) {
		// TODO Auto-generated method stub
		//Do something
		Intent intent = new Intent(this, GetSettings.class);
	    startActivity(intent);
	}
	
	public void sendToNumber(View view){
		Intent intent = new Intent(this, SendToNumber.class);
		EditText phoneNumber = (EditText) findViewById(R.id.entered_number);
		String number = phoneNumber.getText().toString();
		intent.putExtra(PHONE_NUMBER, number);
	    startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        case R.id.action_help:
	        	openHelp();
	        	return true;
	        case R.id.action_about:
	        	openAbout();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void openAbout() {
		// TODO Auto-generated method stub
		Intent i = new Intent(MainActivity.this, ActionbarHelp.class);
		startActivity(i);
	}

	private void openHelp() {
		// TODO Auto-generated method stub
		Intent i = new Intent(MainActivity.this, ActionbarHelp.class);
		startActivity(i);
	}

	private void openSettings() {
		// TODO Auto-generated method stub
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
