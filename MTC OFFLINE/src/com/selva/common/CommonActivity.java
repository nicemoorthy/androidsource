package com.selva.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.selva.custom.MTCArrayAdapter;
import com.selva.custom.MTCLinearAdapter;
import com.selva.mtc.R;
import com.selva.mtc.SearchByRouteActivity;
import com.selva.mtc.SearchByStageActivity;
import com.selva.util.PropertiesReader;

public class CommonActivity extends Activity {
	protected ListView routeListView;
	protected AutoCompleteTextView routeAutoView;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, Menu.FIRST, Menu.FIRST, "EXIT").setIcon(
				android.R.drawable.ic_notification_clear_all);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			moveTaskToBack(true);
			finish();
			break;
		default:
			break;
		}
		return true;
	}

	protected void goToNextScreen(Map<String, String> inpMap,
			Class<?> whichActivity) {
		Intent intent = new Intent(this, whichActivity);
		if (inpMap != null && inpMap.size() > 0) {
			for (String key : inpMap.keySet()) {
				intent.putExtra(key, inpMap.get(key));
			}
		}
		startActivity(intent);
	}

	protected ArrayList<String> getRouteList(String selection) {
		ArrayList<String> routeList = new ArrayList<String>();
		String[] routeArray = PropertiesReader.getRouteValue(selection).split(
				",");
		routeList.addAll(Arrays.asList(routeArray));
		return routeList;
	}

	protected void autoComplete(String whichSearch) {
		if (whichSearch.equals(getRvalue(R.string.route))) {
			ArrayList<String> routeList = PropertiesReader.getRouteList();
			MTCArrayAdapter adapter = new MTCArrayAdapter(this,
					android.R.layout.simple_dropdown_item_1line, routeList);
			routeAutoView.setAdapter(adapter);
		} else {
			ArrayList<String> stageList = PropertiesReader.getStageList();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line, stageList);
			routeAutoView.setAdapter(adapter);
		}
	}

	protected void onAutoCompleteClick(final String whichSearch) {
		routeAutoView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				String selection = (String) parent.getItemAtPosition(position);
				onAutoCompleteClick(selection, whichSearch);
			}
		});
	}

	protected void onAutoCompleteClick(String selection, String whichSearch) {
		if (whichSearch.equals(getRvalue(R.string.route))) {
			String[] boardBusHead = PropertiesReader.getHeadValue(selection)
					.split(",");
			findViewById(R.id.txt1).setVisibility(View.VISIBLE);
			findViewById(R.id.txt2).setVisibility(View.VISIBLE);
			findViewById(R.id.txt3).setVisibility(View.VISIBLE);
			findViewById(R.id.txt4).setVisibility(View.VISIBLE);
			TextView serviceText = (TextView) findViewById(R.id.service_text);
			serviceText.setText(boardBusHead[1]);
			TextView orginText = (TextView) findViewById(R.id.orgin_text);
			orginText.setText(boardBusHead[2]);
			TextView destText = (TextView) findViewById(R.id.dest_text);
			destText.setText(boardBusHead[3]);
			TextView journyText = (TextView) findViewById(R.id.journy_text);
			journyText.setText(boardBusHead[4]);
			populateListView(selection, whichSearch);
		} else {
			populateListView(selection, whichSearch);
		}
	}

	protected void populateListView(String selection, final String whichSearch) {
		ArrayList<String> list = (whichSearch.equals(getRvalue(R.string.route)) ? getRouteList(selection)
				: PropertiesReader.getStageRouteList(selection));
		routeListView.setAdapter(new MTCLinearAdapter(getApplicationContext(),
				R.layout.route_list_view, list));
		routeListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				String selection = (String) parent.getItemAtPosition(position);
				Map<String, String> dataMap = new HashMap<String, String>();
				String mapKey = whichSearch.equals(getRvalue(R.string.route)) ? "StageName"
						: "RouteNum";
				dataMap.put(mapKey, selection);
				Class<?> whichActivity = whichSearch
						.equals(getRvalue(R.string.route)) ? SearchByStageActivity.class
						: SearchByRouteActivity.class;
				goToNextScreen(dataMap, whichActivity);
			}
		});
	}

	protected String getRvalue(int inpR) {
		return getResources().getString(inpR);
	}
}
