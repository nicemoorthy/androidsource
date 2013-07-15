package com.selva.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class PropertiesReader {

	public static String getHeadValue(String inpKey) {
		ResourceBundle rbBundle = ResourceBundle.getBundle("routeHead");
		return rbBundle.getString(inpKey);
	}

	public static String getRouteValue(String inpKey) {
		ResourceBundle rbBundle = ResourceBundle.getBundle("routeStage");
		return rbBundle.getString(inpKey);
	}

	public static ArrayList<String> getRouteList() {
		ResourceBundle rbBundle = ResourceBundle.getBundle("routeStage");
		return Collections.list(rbBundle.getKeys());
	}

	public static ArrayList<String> getStageList() {
		ResourceBundle rbBundle = ResourceBundle.getBundle("routeStage");
		ArrayList<String> keyList = Collections.list(rbBundle.getKeys());
		Set<String> uniqueSet = new HashSet<String>();
		ArrayList<String> stageList = new ArrayList<String>();
		for (String key : keyList) {
			uniqueSet.addAll(Arrays.asList(getRouteValue(key).split(",")));
		}
		stageList.addAll(uniqueSet);
		return stageList;
	}

	public static ArrayList<String> getStageRouteList(String inpKey) {
		ResourceBundle rbBundle = ResourceBundle.getBundle("routeStage");
		ArrayList<String> keyList = Collections.list(rbBundle.getKeys());
		ArrayList<String> stageRouteList = new ArrayList<String>();
		for (String key : keyList) {
			if (getRouteValue(key).contains(inpKey.trim()))
				stageRouteList.add(key);
		}
		return stageRouteList;
	}
}
