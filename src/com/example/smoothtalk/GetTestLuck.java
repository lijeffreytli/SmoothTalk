package com.example.smoothtalk;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class GetTestLuck extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_test_luck);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
