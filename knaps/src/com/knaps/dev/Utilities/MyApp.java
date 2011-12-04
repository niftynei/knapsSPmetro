package com.knaps.dev.Utilities;

import android.content.Context;

public class MyApp extends android.app.Application {
	private static Context context;

	    public void onCreate(){
	        MyApp.context=getApplicationContext();
	    }

	    public static Context getAppContext() {
			return context;
		}

}
