package com.knaps.dev;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class myDB extends Activity {
	private final String TAG = "myDB";
	private SQLiteDatabase db;
	private final Context context;
	private final DataBaseHelper dbhelper;
	public myDB(Context c){
		context = c;
		dbhelper = new DataBaseHelper(context);
	}
	public void close(){
		db.close();
	}
	public void open() throws SQLiteException
	{
		try{
			db = dbhelper.getWritableDatabase();
		}catch (SQLiteException e){
			Log.v("Open database exception caught", e.getMessage());
			db = dbhelper.getReadableDatabase();
		}
	}
	
	public ArrayList<Line> getAllLines(){
		ArrayList<Line> lines = new ArrayList<Line>();
		
		this.open();
    	Cursor c = db.rawQuery("SELECT _id, displayName, number FROM line", null);
    	Log.d(TAG, "Cursor created with " + c.getCount() +" items");
    	this.close();
    	
    	startManagingCursor(c);
    	if(c.moveToFirst()){
    		do{
    			Line temp = new Line(c.getInt(0), c.getString(1), c.getInt(3), TestProjectActivity.alertSubject);
    			//Line temp = new Line(c.getInt(0), , c.getString(2), ,c.getString(4),c.getInt(5),c.getString(6),c.getString(7), c.getDouble(8));
    			lines.add(temp);
    		}while(c.moveToNext());
    	}
    	
		return lines;
	}
	
	public static ArrayList<Line> getLinesByStation(int stationId) {
		// TODO Get All lines by StationID
		return null;
	}
	
	public Line getLine(int lineId){
		// TODO Get line by lineID
		return null;
	}
	
	public static ArrayList<Alert> getAllCurrentAlerts(){
		//TODO: Return all current alerts
		return null;
	}
	public static ArrayList<Station> getAllStations(){
		// TODO get ALL stations
		return null;
	}
	public static ArrayList<Station> getStationsByLine(int lineId) {
		// TODO Auto-generated method stub
		return null;
	}
	public static Station getStation(int stationId){
		// TODO get station by id
		return null;
	}
	public static ArrayList<PlannedRoute> getAllSavedRoutes(){
		// TODO get all saved routes
		return null;
	}
	public static PlannedRoute getSavedRoute(String name){
		// TODO get route by name
		return null;
	}
	
	public static void saveRoute(Route route){
		// TODO Save route to Preferences??
	}
	
	public boolean updateDatabase(){
		// TODO Update the Database from ONLINE (SYNC!)
		return true;
	}
	public Cursor getCursor(String tableName){
		 return db.query(tableName, null, null, null, null, null, null);
	}
	
	public Cursor getCursor(String tableName, String[] columns, String where, String[] args, String orderBy){
		return db.query(tableName, columns, where, args, null, null, orderBy);
	}


}
