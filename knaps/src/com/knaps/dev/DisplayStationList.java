package com.knaps.dev;

import java.util.ArrayList;
import java.util.WeakHashMap;

import com.knaps.dev.R;
import com.knaps.dev.DAL.DataAccessor;
import com.knaps.dev.Models.Station;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayStationList extends ListActivity{
	private DataAccessor da;
	final WeakHashMap<Long, Object> hasher = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		da = new DataAccessor(this.getApplicationContext());
		this.setListAdapter(new StationAdapter(this));
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Object obj = getListView().getItemAtPosition(position);
		
		Intent intent = new Intent(DisplayStationList.this, StationView.class);
		intent.putExtra("station", (Station)obj);
		startActivity(intent);
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
		    	stations = da.getAllStations();
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
				v.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
					}
				});
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
