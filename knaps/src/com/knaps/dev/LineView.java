package com.knaps.dev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.knaps.dev.Models.Line;
import com.knaps.dev.Models.Station;
import com.knaps.dev.Views.AlertView;
import com.knaps.dev.Views.NavButtonsActivity;

public class LineView extends NavButtonsActivity implements OnCustomClickListener {
	private Line _line;
	private StationAdapter mAdapter;
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
		mAdapter = new StationAdapter(getApplicationContext(), _line.getId(), this);
		lv.setAdapter(mAdapter);
		
		TextView tv = (TextView)findViewById(R.id.line_name_for_view);
		tv.setText(line.getDisplayName());
		
		AlertView va = (AlertView)findViewById(R.id.line_alert);
		
		if (line.getAlerts() == null || line.getAlerts().size() == 0){
			va.setVisibility(View.GONE);
		}
		else {
		}
	}
	public void onCustomClick(View aView, int position) {
		Object obj = mAdapter.getItem(position);
		
		Intent intent = new Intent(this, StationView.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("station", (Station)obj);
		startActivity(intent);
		
	}
}
