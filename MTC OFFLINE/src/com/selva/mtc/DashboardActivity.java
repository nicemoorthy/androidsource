package com.selva.mtc;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;

import com.selva.common.CommonActivity;

public class DashboardActivity extends CommonActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashbord);
	}

	public void onClickFeature(View v) {
		int id = v.getId();
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("RouteNum", "");
		dataMap.put("StageName", "");
		switch (id) {
		case R.id.route_search:
			goToNextScreen(dataMap, SearchByRouteActivity.class);
			break;
		case R.id.stage_search:
			goToNextScreen(dataMap, SearchByStageActivity.class);
			break;
		default:
			break;
		}
	}
}