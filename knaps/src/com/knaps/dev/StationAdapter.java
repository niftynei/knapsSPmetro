package com.knaps.dev;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knaps.dev.DAL.DataAccessor;
import com.knaps.dev.Models.Station;

public class StationAdapter extends BaseAdapter {
	private final String TAG = "StationAdapter";
	private LayoutInflater mInf;
	private ArrayList<Station> stations;
	private DataAccessor mDba;
	private OnCustomClickListener mCallback;
	
	public StationAdapter(Context c, int lineId, OnCustomClickListener callback){
		mInf = LayoutInflater.from(c);
		stations = new ArrayList<Station>();
		mDba = new DataAccessor(c);
		mCallback = callback;
		getStations(lineId);
	}
	
	public void getStations(int lineId){
	    	if (lineId < 0)
	    		stations = mDba.getAllStations();
	    	else
	    		stations = mDba.getStationsByLine(lineId);
	}

	public int getCount(){ return stations.size();}

	public Station getItem(int i) { return stations.get(i);}

	public long getItemId(int i) {
		return i;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		final ViewHolder holder;
		Log.d(TAG, "get view reached!");
		View v = arg1;
		if ((v==null) || (v.getTag() == null)){
			v = mInf.inflate(R.layout.stationrow, null);
			holder = new ViewHolder();
			holder.mDisplayName = (TextView)v.findViewById(R.id.station_name);
			holder.mLineCount = (TextView)v.findViewById(R.id.line_count);
			holder.mStatus = (TextView)v.findViewById(R.id.status);
			v.setTag(holder);
		}else{
			holder=(ViewHolder)v.getTag();
		}
		
	Log.d(TAG, "heading to station creation");

	holder.station = getItem(arg0);
	holder.mDisplayName.setText(holder.station.getDisplayName());
	holder.mLineCount.setText(Integer.toString(holder.station.getLines().size()));
	//holder.mStatus.setText(holder.station.getStatus().toString());
	v.setTag(holder);
	v.setOnClickListener(new CustomOnClickListener(mCallback, arg0));
	return v;
	}
	
public class ViewHolder{
	Station station;
	TextView mDisplayName;
	TextView mLineCount;
	TextView mStatus;
}
}
