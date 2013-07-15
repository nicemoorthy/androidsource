package com.selva.mtc;

import android.os.Bundle;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.selva.common.CommonActivity;

public class SearchByStageActivity extends CommonActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.stage_search);
		routeListView = (ListView) findViewById(R.id.route_list);
		routeAutoView = (AutoCompleteTextView) findViewById(R.id.route_complete);
		autoComplete(getRvalue(R.string.stage));
		String passRouteNo = getIntent().getExtras().getString("StageName");
		if (passRouteNo.length() > 0) {
			routeAutoView.setText(passRouteNo);
			onAutoCompleteClick(passRouteNo, getRvalue(R.string.stage));
		} else {
			onAutoCompleteClick(getRvalue(R.string.stage));
		}
	}
}