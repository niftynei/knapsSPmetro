package com.knaps.dev;

import java.util.ArrayList;

import com.knaps.dev.R;
import com.knaps.dev.DAL.DataAccessor;
import com.knaps.dev.Models.Line;
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

public class DisplayLineList extends ListActivity{
	DataAccessor dba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		dba = new DataAccessor(this.getApplicationContext());
		
		super.onCreate(savedInstanceState);
		this.setListAdapter(new LineAdapter(this));
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Object obj = getListView().getItemAtPosition(position);
		
		Intent intent = new Intent(DisplayLineList.this, LineView.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("line", (Line)obj);
		startActivity(intent);
	}
	
	private class LineAdapter extends BaseAdapter {
		private final String TAG = "LineAdapter";
		private LayoutInflater mInf;
		private ArrayList<Line> lines;
		public LineAdapter(Context c){
			mInf = LayoutInflater.from(c);
			lines = new ArrayList<Line>();
			getLines(c);
		}
		public void getLines(Context c){
			lines = dba.getAllLines();
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
				v = mInf.inflate(R.layout.linerow, null);
				holder = new ViewHolder();
				holder.mDisplayName = (TextView)v.findViewById(R.id.name);
				holder.mColor = (TextView)v.findViewById(R.id.color);
				holder.mTarifa = (TextView)v.findViewById(R.id.tarifa);
				v.setTag(holder);
			}else{
				holder=(ViewHolder)v.getTag();
			}
			
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
