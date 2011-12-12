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

public class StationView extends NavButtonsActivity {
	private Station _station;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// Pull Saved Station out of Passed Intent
		Station station = getIntent().getParcelableExtra("station");
		_station = station;
		setView(station);
	}
	@Override
	protected void onNewIntent(Intent intent){
		Station station = intent.getParcelableExtra("station");
		_station = station;
		
		setView(station);
	}
	private void setView(Station station) {
		setContentView(R.layout.station_view);
		
		ListView lv = (ListView)findViewById(R.id.station_line_list);
		lv.setAdapter(new LineAdapter(this));
		
		TextView tv = (TextView)findViewById(R.id.station_name_for_view);
		tv.setText(station.getDisplayName());
		
		AlertView va = (AlertView)findViewById(R.id.station_alert);
		
		if (station.getAlerts() == null || station.getAlerts().size() == 0){
			va.setVisibility(View.GONE);
		}
		else {
			TextView alertText = (TextView)va.findViewById(R.id.station_alert_text);
			alertText.setText(station.getAlerts().toString());
		}
	}
	private class LineAdapter extends BaseAdapter{

		private final String TAG = "LineAdapter";
		private LayoutInflater mInf;
		private ArrayList<Line> lines;
		public LineAdapter(Context c){
			mInf = LayoutInflater.from(c);
			lines = new ArrayList<Line>();
			getLines(c);
		}
		public void getLines(Context c){
			DataAccessor dba = new DataAccessor(MyApp.getAppContext());
			dba.open();
			lines = dba.getLinesByStation(_station.getId());
		}

		public int getCount(){ return lines.size();}

		public Line getItem(int i) { return lines.get(i);}

		public long getItemId(int i) {
			return i;
		}

		public View getView(int arg0, View arg1, ViewGroup arg2) {
			final ViewHolder holder;
			Log.d(TAG, "get view reached");
			View v = arg1;
			if ((v==null) || (v.getTag() == null)){
				Log.d(TAG, "v was null");
				v = mInf.inflate(R.layout.linerow, null);
				Log.d(TAG, "v inflated "+ v.getId());
				holder = new ViewHolder();
				holder.mDisplayName = (TextView)v.findViewById(R.id.name);
				Log.d(TAG, Double.toString(R.id.name));
				holder.mColor = (TextView)v.findViewById(R.id.color);
				Log.d(TAG, Double.toString(R.id.color));
				holder.mTarifa = (TextView)v.findViewById(R.id.tarifa);
				Log.d(TAG, Double.toString(R.id.tarifa));
				v.setTag(holder);
			}else{
				holder=(ViewHolder)v.getTag();
			}
			
		Log.d(TAG, "heading to line creation");
		
		holder.line = getItem(arg0);
		holder.mDisplayName.setText(holder.line.getDisplayName());
		holder.mColor.setText(holder.line.getColor());
		holder.mTarifa.setText(Double.toString(holder.line.getTarifa()));
		v.setTag(holder);
		return v;
		}
		
	public class ViewHolder{
		Line line;
		TextView mDisplayName;
		TextView mColor;
		TextView mTarifa;
	}
	}
}
