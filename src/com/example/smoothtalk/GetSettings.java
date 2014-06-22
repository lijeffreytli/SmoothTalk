package com.example.smoothtalk;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class GetSettings extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_settings);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
