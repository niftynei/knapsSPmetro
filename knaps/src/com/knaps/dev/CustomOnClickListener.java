package com.knaps.dev;

import android.view.View;
import android.view.View.OnClickListener;

public class CustomOnClickListener implements OnClickListener {
	private int  position;
	private OnCustomClickListener callback;
	
	public CustomOnClickListener(OnCustomClickListener callback, int position){
		this.position = position;
		this.callback = callback;
	}
	public void onClick(View v) {
		callback.onCustomClick(v, position);
	}

}
