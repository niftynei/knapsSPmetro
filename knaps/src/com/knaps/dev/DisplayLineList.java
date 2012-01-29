package com.knaps.dev;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.knaps.dev.Models.Line;

public class DisplayLineList extends ListActivity implements OnCustomClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setListAdapter(new LineAdapter(getApplicationContext(), -1, this));
	}

	public void onCustomClick(View aView, int position) {
		Object obj = getListView().getItemAtPosition(position);
		
		Intent intent = new Intent(DisplayLineList.this, LineView.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("line", (Line)obj);
		startActivity(intent);	
	}
}
