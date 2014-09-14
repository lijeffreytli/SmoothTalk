package com.example.smoothtalk;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.smoothtalk.MESSAGE";	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		// Remove the name of the app from action bar
		getActionBar().setDisplayShowTitleEnabled(false);
		
//		TextView txtButtonEnter = (TextView) findViewById(R.id.button_enter_number);
//		Typeface fontButtonEnter = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//		txtButtonEnter.setTypeface(fontButtonEnter);
//		
//		TextView txtButtonContacts = (TextView) findViewById(R.id.button_contacts);
//		Typeface fontButtonContacts = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//		txtButtonContacts.setTypeface(fontButtonContacts);
//		
//		TextView txtButtonTestLuck = (TextView) findViewById(R.id.button_test_your_luck);
//		Typeface fontButtonTestLuck = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//		txtButtonTestLuck.setTypeface(fontButtonTestLuck);
//		
//		TextView txtButtonSettings = (TextView) findViewById(R.id.button_settings);
//		Typeface fontButtonSettings = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//		txtButtonSettings.setTypeface(fontButtonSettings);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public void getTestLuck(View view) {
		Intent intent = new Intent(this, GetTestLuck.class);
	    startActivity(intent);
	}
	
	public void getContacts(View view) {
		Intent intent = new Intent(this, GetContacts.class);
	    startActivity(intent);
	}
	
	public void getSettings(View view) {
		Intent intent = new Intent(this, GetSettings.class);
	    startActivity(intent);
	}
	
	public void sendToNumber(View view) {
		Intent intent = new Intent(this, SendToNumber.class);
	    startActivity(intent);
	}
	
	
	
	/* Creates the menu for the action bar (Unused Code) */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        default:
	            return super.onOptionsItemSelected(item);
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
			
			/* Set the fonts for the main page buttons: Yay it works! */
			TextView txtButtonEnter = (TextView) rootView.findViewById(R.id.button_enter_number);
			Typeface fontButtonEnter = Typeface.createFromAsset(getActivity().getAssets(), "AliquamREG.ttf");
			txtButtonEnter.setTypeface(fontButtonEnter);
			
			TextView txtButtonContacts = (TextView) rootView.findViewById(R.id.button_contacts);
			Typeface fontButtonContacts = Typeface.createFromAsset(getActivity().getAssets(), "AliquamREG.ttf");
			txtButtonContacts.setTypeface(fontButtonContacts);
			
			TextView txtButtonTestLuck = (TextView) rootView.findViewById(R.id.button_test_your_luck);
			Typeface fontButtonTestLuck = Typeface.createFromAsset(getActivity().getAssets(), "AliquamREG.ttf");
			txtButtonTestLuck.setTypeface(fontButtonTestLuck);
			
//			TextView txtButtonSettings = (TextView) rootView.findViewById(R.id.button_settings);
//			Typeface fontButtonSettings = Typeface.createFromAsset(getActivity().getAssets(), "AliquamREG.ttf");
//			txtButtonSettings.setTypeface(fontButtonSettings);
			
			return rootView;
		}
	}
	
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//	       View v = inflater.inflate(R.layout.fragment_main, container, false);
////	       TextView txt = (TextView) v.findViewById(R.id.Zipcode);
////	       Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/customfont.ttf");
////	       txt.setTypeface(font); 
//	       
//			TextView txtButtonEnter = (TextView) findViewById(R.id.button_enter_number);
//			Typeface fontButtonEnter = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//			txtButtonEnter.setTypeface(fontButtonEnter);
//			
//			TextView txtButtonContacts = (TextView) findViewById(R.id.button_contacts);
//			Typeface fontButtonContacts = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//			txtButtonContacts.setTypeface(fontButtonContacts);
//			
//			TextView txtButtonTestLuck = (TextView) findViewById(R.id.button_test_your_luck);
//			Typeface fontButtonTestLuck = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//			txtButtonTestLuck.setTypeface(fontButtonTestLuck);
//			
//			TextView txtButtonSettings = (TextView) findViewById(R.id.button_settings);
//			Typeface fontButtonSettings = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
//			txtButtonSettings.setTypeface(fontButtonSettings);
//	       
//	       return v;
//	}

}
