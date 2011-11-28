package com.knaps.dev;

import java.util.ArrayList;

import com.knaps.dev.R;
import com.knaps.dev.DAL.DataHelper;
import com.knaps.dev.Models.Line;
import com.knaps.dev.Models.Station;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DisplayStationList extends ListActivity{
	DataHelper dba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		dba = new DataHelper(this.getBaseContext());
		dba.open();
		
		super.onCreate(savedInstanceState);
		this.setListAdapter(new StationAdapter(this));
	}
	
	private class StationAdapter extends BaseAdapter {
		private final String TAG = "StationAdapter";
		private LayoutInflater mInf;
		private ArrayList<Station> stations;
		public StationAdapter(Context c){
			mInf = LayoutInflater.from(c);
			stations = new ArrayList<Station>();
			getStations(c);
		}
		    public void getStations(Context c){
		    	dba = new DataHelper(c);
		    	stations = dba.getAllStations();
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
				Log.d(TAG, "v was null");
				v = mInf.inflate(R.layout.stationrow, null);
				Log.d(TAG, "v inflated "+ v.getId());
				holder = new ViewHolder();
				holder.mDisplayName = (TextView)v.findViewById(R.id.station_name);
				Log.d(TAG, Double.toString(R.id.station_name));
				holder.mLineCount = (TextView)v.findViewById(R.id.line_count);
				Log.d(TAG, Double.toString(R.id.line_count));
				holder.mStatus = (TextView)v.findViewById(R.id.status);
				Log.d(TAG, Double.toString(R.id.status));
				v.setTag(holder);
			}else{
				holder=(ViewHolder)v.getTag();
			}
			
		Log.d(TAG, "heading to line creation");
		
		holder.station = getItem(arg0);
		holder.mDisplayName.setText(holder.station.getDisplayName());
		holder.mLineCount.setText(holder.station.getLines().size());
		holder.mStatus.setText(holder.station.getStatus().toString());
		v.setTag(holder);
		return v;
		}
		
	public class ViewHolder{
		Station station;
		TextView mDisplayName;
		TextView mLineCount;
		TextView mStatus;
	}
	}

}
