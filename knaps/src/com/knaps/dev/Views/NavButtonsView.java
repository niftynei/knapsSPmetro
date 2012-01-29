package com.knaps.dev.Views;

import com.knaps.dev.MainActivity;
import com.knaps.dev.R;
import com.knaps.dev.StationView;
import com.knaps.dev.Models.Station;
import com.knaps.dev.Utilities.MyApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class NavButtonsView extends LinearLayout {
	Activity mActivity;
	public NavButtonsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater layoutInflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflator.inflate(R.layout.nav_buttons, this);
	}
	
	}

