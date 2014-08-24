package com.example.smoothtalk;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class GetTestLuck extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_test_luck);
		
		TextView txtAre = (TextView) findViewById(R.id.textAre);
		Typeface fontAre = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtAre.setTypeface(fontAre);
		
		TextView txtYou = (TextView) findViewById(R.id.textYou);
		Typeface fontYou = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtYou.setTypeface(fontYou);
		
		TextView txtSure = (TextView) findViewById(R.id.textSure);
		Typeface fontSure = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtSure.setTypeface(fontSure);
		
		TextView txtWarning = (TextView) findViewById(R.id.textWarning);
		Typeface fontWarning = Typeface.createFromAsset(getAssets(), "AliquamREG.ttf");
		txtWarning.setTypeface(fontWarning);
	}
}
