package com.example.smoothtalk;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

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
		Intent intent = new Intent(this, getContacts.class);
	    startActivity(intent);
	}
	
	
	public void getSettings(View view) {
		// TODO Auto-generated method stub
		//Do something
		Intent intent = new Intent(this, getSettings.class);
	    startActivity(intent);
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	super.onActivityResult(reqCode, resultCode, data);

	switch (reqCode) {
		case (0) :
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c =  managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {
					String name = c.getString(c.getColumnIndexOrThrow(People.NAME));
					// TODO Whatever you want to do with the selected contact name.
				}
			}
		break;
		}
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
