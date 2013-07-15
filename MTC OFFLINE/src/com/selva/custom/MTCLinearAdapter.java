package com.selva.custom;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.selva.mtc.R;

public class MTCLinearAdapter extends ArrayAdapter<String> {
	private ArrayList<String> inpList;
	private Context context;

	public MTCLinearAdapter(Context context, int textViewResourceId,
			List<String> list) {
		super(context, textViewResourceId, list);
		this.inpList = (ArrayList<String>) list;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View currentView = convertView;
		if (currentView == null) {
			LayoutInflater inflaterView = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			currentView = inflaterView.inflate(R.layout.route_list_view, null);
		}
		final String stopTxt = inpList.get(position);
		TextView txtDesc = (TextView) currentView.findViewById(R.id.route_txt);
		ImageView storeImage = (ImageView) currentView
				.findViewById(R.id.imgIcon);
		if (storeImage != null) {
			storeImage.setImageResource(R.drawable.stop);
		}
		txtDesc.setText(stopTxt);
		return currentView;
	}
}
