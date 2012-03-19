package com.knaps.dev.Views;

import com.knaps.dev.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class StationMapView extends NavButtonsActivity {
	private final String TAG = "StationMapView";
	private Bitmap mBitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//String imageUri = savedInstanceState.getString("stationMap");	
		setView();
	}

	private void setView() {
		ImageView image = new ImageView(this);
		Log.d(TAG, "new image view created");
		mBitmap = getBitmap(R.drawable.alto_ipiranga);
		image.setImageBitmap(mBitmap);
		Log.d(TAG, "bitmap height: " + mBitmap.getHeight());
	    image.setAdjustViewBounds(true);
	    image.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	    
	    setContentView(image);
	}

	private Bitmap getBitmap(int resource) {
		BitmapFactory factory = new BitmapFactory();
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		factory.decodeResource(getResources(), resource, options);
		options.inJustDecodeBounds = false;
		Log.d(TAG, "width: " + options.outWidth + " height: " + options.outHeight);
		if (options.outWidth>500){
			options.inSampleSize = 4;
			mBitmap = factory.decodeResource(getResources(), resource, options);
		}
		else mBitmap = factory.decodeResource(getResources(), resource, options);
		
		return mBitmap;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setView();
	}

	@Override
	protected void onPause() {
		mBitmap.recycle();
		Toast.makeText(this, "Image Recycled", Toast.LENGTH_SHORT);
		super.onPause();
	}

	@Override
	protected void onStop() {
		mBitmap.recycle();
		Toast.makeText(this, "Image Recycled", Toast.LENGTH_SHORT);
		super.onStop();
	}
	
	
}
