package com.knaps.dev;

import java.util.ArrayList;

import com.knaps.dev.DAL.DataAccessor;
import com.knaps.dev.Models.Line;
import com.knaps.dev.Models.Station;
import com.knaps.dev.Utilities.MyApp;
import com.knaps.dev.Views.AlertView;
import com.knaps.dev.Views.NavButtonsActivity;

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

public class LineView extends NavButtonsActivity {
	private Line _line;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// Pull Saved Station out of Passed Intent
		Line line = getIntent().getParcelableExtra("line");
		_line = line;
		setView(line);
	}
	@Override
	protected void onNewIntent(Intent intent){
		Line line = intent.getParcelableExtra("line");
		_line = line;
		
		setView(line);
	}
	private void setView(Line line) {
		setContentView(R.layout.line_view);
		
		ListView lv = (ListView)findViewById(R.id.line_station_list);
		lv.setAdapter(new LineAdapter(this));
		
		TextView tv = (TextView)findViewById(R.id.line_name_for_view);
		tv.setText(line.getDisplayName());
		
		AlertView va = (AlertView)findViewById(R.id.line_alert);
		
		if (line.getAlerts() == null || line.getAlerts().size() == 0){
			va.setVisibility(View.GONE);
		}
		else {
		}
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Object obj = getListView().getItemAtPosition(position);
		
		Intent intent = new Intent(DisplayStationList.this, StationView.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("station", (Station)obj);
		startActivity(intent);
	}
	private class LineAdapter extends BaseAdapter{

		private final String TAG = "LineAdapter";
		private LayoutInflater mInf;
		private ArrayList<Station> stations;
		public LineAdapter(Context c){
			mInf = LayoutInflater.from(c);
			stations = new ArrayList<Station>();
			getLines(c);
		}
		public void getLines(Context c){
			DataAccessor dba = new DataAccessor(MyApp.getAppContext());
			stations = dba.getStationsByLine(_line.getId());
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
