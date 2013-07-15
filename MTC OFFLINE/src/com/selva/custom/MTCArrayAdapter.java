package com.selva.custom;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

public class MTCArrayAdapter extends ArrayAdapter<String> {
	private Filter filter;
	private ArrayList<String> inpOrigList;
	private ArrayList<String> inpTempList;
	private ArrayList<String> filterList;

	public MTCArrayAdapter(Context context, int resource,
			ArrayList<String> objects) {
		super(context, resource, objects);
		this.inpOrigList = objects;
	}

	@Override
	public Filter getFilter() {
		if (filter == null) {
			filter = new MTCFilter();
		}
		return filter;
	}

	protected class MTCFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filtResult = new FilterResults();
			if (inpTempList == null) {
				synchronized (this) {
					inpTempList = new ArrayList<String>(inpOrigList);
				}
			}
			if (constraint == null || constraint.length() == 0) {
				synchronized (this) {
					final ArrayList<String> oldArray = new ArrayList<String>(
							inpTempList);
					filtResult.values = oldArray;
					filtResult.count = oldArray.size();
				}
			} else {
				final ArrayList<String> oldArray = inpTempList;
				final int count = oldArray.size();
				final ArrayList<String> newList = new ArrayList<String>(count);
				for (String checkStr : oldArray) {
					if (checkStr.toLowerCase().contains(
							constraint.toString().toLowerCase())) {
						newList.add(checkStr);
					}
				}
				filtResult.values = newList;
				filtResult.count = newList.size();
			}
			return filtResult;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			filterList = (ArrayList<String>) results.values;
			if (filterList != null && filterList.size() > 0) {
				clear();
				for (String filterStr : filterList) {
					add(filterStr);
				}
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}

	}

}
