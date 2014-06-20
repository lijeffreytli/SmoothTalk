package com.example.smoothtalk;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;



public class ActionbarHelp extends ActionBarActivity{

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar_help);
 
        // get action bar   
        ActionBar actionBar = getActionBar();
 
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
