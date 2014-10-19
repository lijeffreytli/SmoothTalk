package com.example.smoothtalk;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class GetSettings extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		
	}
}
