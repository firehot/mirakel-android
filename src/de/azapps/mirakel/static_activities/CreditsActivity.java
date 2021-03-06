package de.azapps.mirakel.static_activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import de.azapps.mirakel.R;

public class CreditsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
		TextView creditText = (TextView) findViewById(R.id.credit_text);
		creditText.setText(Html.fromHtml(getString(R.string.credit)));
		creditText.setMovementMethod(LinkMovementMethod.getInstance());
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
