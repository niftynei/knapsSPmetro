package com.knaps.dev;

import com.knaps.dev.Models.Station;
import com.knaps.dev.Views.AlertView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StationView extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// Pull Saved Station out of Passed Intent
		Intent receivedIntent = getIntent();
		Station station = receivedIntent.getParcelableExtra("station");
		
		setContentView(R.layout.station_view);
		
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
		
		setStationPrefButtons(station);
	}
	
	private void setStationPrefButtons(final Station station) {
		Button home = (Button)findViewById(R.id.home_button);
		Button current = (Button)findViewById(R.id.current_button);
		Button map = (Button)findViewById(R.id.map_button);
		
		home.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		         AlertDialog.Builder adb = new AlertDialog.Builder(StationView.this);
		         adb.setTitle("Set Home Station?");
		         adb.setMessage("Your Home Station has been set to " + station.getDisplayName());
		         adb.setPositiveButton("Ok", null);
		         adb.show();
			}
		});
		
		current.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		         AlertDialog.Builder adb = new AlertDialog.Builder(StationView.this);
		         adb.setTitle("Set Home Station?");
		         adb.setMessage("Your Home Station has been set to " + station.getDisplayName());
		         adb.setPositiveButton("Ok", null);
		         adb.show();
			}
		});
		
		map.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent showMap = new Intent(StationView.this, MainActivity.class);
        		startActivity(showMap);
			}
		});
	}
}
