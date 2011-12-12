package com.knaps.dev.Views;

import com.knaps.dev.MainActivity;
import com.knaps.dev.Utilities.MyApp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class NavButtonsActivity extends Activity {
	public void goHome(View v){
		Intent goHome = new Intent(MyApp.getAppContext(), MainActivity.class);
		startActivity(goHome);
	}
	public void showSystemMap(View v){
		  Intent showMap = new Intent(MyApp.getAppContext(), MainActivity.class);
	         startActivity(showMap);
	}
		public void goSearch(View v){
			Intent goToSearch = new Intent(MyApp.getAppContext(), MainActivity.class);
    		startActivity(goToSearch);	
		}
	
		public void createRoute(View v){
				Intent createRoute = new Intent(MyApp.getAppContext(), MainActivity.class);
        		startActivity(createRoute);
			}
		

}
