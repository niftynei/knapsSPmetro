package com.knaps.dev;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knaps.dev.DAL.DataAccessor;
import com.knaps.dev.Models.Line;
import com.knaps.dev.Utilities.MyApp;

public class LineAdapter extends BaseAdapter {
	private DataAccessor mDba;
	private final String TAG = "LineAdapter";
	private LayoutInflater mInf;
	private ArrayList<Line> lines;
	private OnCustomClickListener callback;

	public LineAdapter(Context c, int stationId, OnCustomClickListener callback){
			mInf = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			lines = new ArrayList<Line>();
			mDba = new DataAccessor(c);
			this.callback = callback;
			
			getLines(stationId);
		}
		public void getLines(int stationId){
			if (stationId < 0)
				lines = mDba.getAllLines();
			else 
				lines = mDba.getLinesByStation(stationId);
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
		v.setOnClickListener(new CustomOnClickListener(callback, arg0));
		return v;
		}
		
	public class ViewHolder{
		Line line;
		TextView mDisplayName;
		TextView mColor;
		TextView mTarifa;
	}
	}