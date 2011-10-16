package com.knaps.dev.Views;

import java.util.ArrayList;

import com.knaps.dev.R;
import com.knaps.dev.DAL.DataHelper;
import com.knaps.dev.Models.Line;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DisplayLines extends ListActivity{
	DataHelper dba;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		dba = new DataHelper(this.getBaseContext());
		dba.open();
		//setContentView(R.layout.lines);
		
		super.onCreate(savedInstanceState);
		this.setListAdapter(new LineAdapter(this));
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
		    	dba = new DataHelper(c);
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
