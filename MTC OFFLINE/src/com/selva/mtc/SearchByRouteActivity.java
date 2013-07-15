package com.selva.mtc;

import android.os.Bundle;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.selva.common.CommonActivity;

public class SearchByRouteActivity extends CommonActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.route_search);
		routeListView = (ListView) findViewById(R.id.route_list);
		routeAutoView = (AutoCompleteTextView) findViewById(R.id.route_complete);
		autoComplete(getRvalue(R.string.route));
		String passRouteNo = getIntent().getExtras().getString("RouteNum");
		if (passRouteNo.length() > 0) {
			routeAutoView.setText(passRouteNo);
			onAutoCompleteClick(passRouteNo, getRvalue(R.string.route));
		} else {
			onAutoCompleteClick(getRvalue(R.string.route));
		}
	}
}