package com.knaps.dev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.knaps.dev.Models.Line;
import com.knaps.dev.Models.Station;
import com.knaps.dev.Views.AlertView;
import com.knaps.dev.Views.NavButtonsActivity;
import com.knaps.dev.Views.StationMapView;

public class StationView extends NavButtonsActivity implements OnCustomClickListener {
	private Station _station;
	private LineAdapter mAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
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
		mAdapter = new LineAdapter(getApplicationContext(), _station.getId(), this);
		lv.setAdapter(mAdapter);
		
		TextView tv = (TextView)findViewById(R.id.station_name_for_view);
		tv.setText(station.getDisplayName());

		Button mapButton = (Button)findViewById(R.id.home_button);
		mapButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), StationMapView.class);
				intent.putExtra("stationMap", _station.getMapURI());
				startActivity(intent);
			}
			
		});
		
		AlertView va = (AlertView)findViewById(R.id.station_alert);
		
		if (station.getAlerts() == null || station.getAlerts().size() == 0){
			va.setVisibility(View.GONE);
		}
		else {
			TextView alertText = (TextView)va.findViewById(R.id.station_alert_text);
			alertText.setText(station.getAlerts().toString());
		}
	}

	public void onCustomClick(View aView, int position) {
		Object obj = mAdapter.getItem(position);
		
		Intent intent = new Intent(this, LineView.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("line", (Line)obj);
		startActivity(intent);
	}
}
