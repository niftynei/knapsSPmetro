package com.knaps.dev.Utilities;

import java.io.*;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.*;

public class AppPrefs {
	
	     private SharedPreferences appSharedPrefs;
	     private Editor prefsEditor;
	     private static AppPrefs uniqueAppsPrefs = new AppPrefs(MyApp.getContext());

	     private AppPrefs(Context context)
	     {
	         this.appSharedPrefs = context.getSharedPreferences(Constants.SHARED_PREFS, Activity.MODE_PRIVATE);
	         this.prefsEditor = appSharedPrefs.edit();
	     }
	     public static AppPrefs getAppPrefs() {
	    	 return uniqueAppsPrefs;
	     }

	     public Serializable getSerializable(String subjectName) {
	         return stringToObject(appSharedPrefs.getString(subjectName, ""));
	     }

	     public void saveObsSubject(Serializable o, String subjectName) {
	         prefsEditor.putString(subjectName, objectToString(o));
	         prefsEditor.commit();
	     }
	     
		private String objectToString(Serializable object) {
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    try {
		        new ObjectOutputStream(out).writeObject(object);
		        byte[] data = out.toByteArray();
		        out.close();
		
		        out = new ByteArrayOutputStream();
		        Base64OutputStream b64 = new Base64OutputStream(out, 0);
		        b64.write(data);
		        b64.close();
		        out.close();
		
		        return new String(out.toByteArray());
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return null;
		}

		private Serializable stringToObject(String encodedObject) {
		    try {
		        return (Serializable) new ObjectInputStream(new Base64InputStream(
		                new ByteArrayInputStream(encodedObject.getBytes()), 0)).readObject();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		}
}
