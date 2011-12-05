package com.knaps.dev.Views;


import com.knaps.dev.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class NavButtonsView extends LinearLayout  {

	public NavButtonsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater layoutInflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflator.inflate(R.layout.nav_buttons, this);
	}
}
