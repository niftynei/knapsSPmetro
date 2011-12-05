package com.knaps.dev.Views;


import com.knaps.dev.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AlertView extends RelativeLayout  {

	public AlertView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater layoutInflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflator.inflate(R.layout.alert_row, this);
	}
}
