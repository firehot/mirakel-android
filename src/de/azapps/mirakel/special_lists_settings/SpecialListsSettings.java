package de.azapps.mirakel.special_lists_settings;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.azapps.mirakel.R;
import de.azapps.mirakel.model.list.SpecialList;

public class SpecialListsSettings extends Activity {

	private static final String TAG = "SpecialListsSettings";
	private static final int requestCode=0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_special_lists_settings);
		// Show the Up button in the action bar.
		if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB)
			getActionBar().setDisplayHomeAsUpEnabled(true);
		
		update();
	}

	private void update() {
		ListView listView=(ListView) findViewById(R.id.special_lists_list);
		final List<SpecialList> slists=SpecialList.allSpecial(true);
		List<String> listContent=new ArrayList<String>();
		for(SpecialList list : slists) {
			listContent.add(list.getName());
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listContent);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View item,
					int position, long id) {
				// TODO Remove Bad Hack
				SpecialList sl = slists.get((int) id);
				editSList(sl);
			}
		});
	}
	
	private void editSList(final SpecialList slist){
		Intent intent=new Intent(this, SpecialListSettingsActivity.class);
		intent.putExtra(SpecialListSettingsActivity.SLIST_ID, -slist.getId());
		startActivityForResult(intent, requestCode);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.special_lists_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.menu_new_special_list:
			Log.e(TAG,"new SpecialList");
			SpecialList newList=SpecialList.newSpecialList("NewList", "", false);
			editSList(newList);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		update();
	}

}
