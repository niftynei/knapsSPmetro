package com.knaps.dev;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.knaps.spmetro.Enums.*;

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
		this.open();
		Cursor c = db.rawQuery("SELECT " +
								"l._id" + 
								", l.name" +
								", l.displayName" +
								", l.number" +
								", l.color" +
								", c.name" +
								", l.iconURL"+
								", l.imgURL"+
								", l.tarifa" +
								"FROM line l", null);
					
		this.close();
		return getLineObjectArray(c);
	}
	
	public ArrayList<Line> getLinesByStation(int stationId) {
		this.open();
		Cursor c = db.rawQuery("SELECT " +
								"l._id" + 
								", l.name" +
								", l.displayName" +
								", l.number" +
								", l.color" +
								", c.name" +
								", l.iconURL"+
								", l.imgURL"+
								", l.tarifa" +
								"FROM line l"+ 
								"INNER JOIN stationline sl"+
								"INNER JOIN company c ON c._id = l.companyID" +
								"WHERE sl.stationId = ? AND sl.lineID = l._id", new String[] {Integer.toString(stationId)});
					
		this.close();
		return getLineObjectArray(c);
	}
	
	public Line getLine(int lineId){
		this.open();
		Cursor c = db.rawQuery("SELECT " +
								"l._id" + 
								", l.name" +
								", l.displayName" +
								", l.number" +
								", l.color" +
								", c.name" +
								", l.iconURL"+
								", l.imgURL"+
								", l.tarifa" +
								"FROM line l"+ 
								"INNER JOIN company c ON c._id = l.companyID" +
								"WHERE l._id = ?", new String[] {Integer.toString(lineId)});
					
		this.close();
		
		return getLineObjectArray(c).get(0);
	}
	
	public ArrayList<Alert> getAllCurrentAlerts(){
		this.open();
		Cursor c = db.rawQuery("select" + 
							    "Message," +
								"Created," +
								"StatusId" +
								"from alert a", null);
		this.close();
		
		startManagingCursor(c);
		ArrayList<Alert> alerts = new ArrayList();
		
		do(c.moveToFirst()){
			LineStatus ls = new LineStatus(c.getInt(2));
			Alert a = new Alert(c.getString(0), c.getString(1), c.getInt(2))
		}
		
		
		return null;
	}
	public static ArrayList<Alert> getAlertsForStation(int stationId){
		
		return null;
	}
	public static ArrayList<Alert> getAlertsForLine(int lineId){
		
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

	private ArrayList<Line> getLineObjectArray(Cursor c) {
		startManagingCursor(c);
		ArrayList<Line> lines = new ArrayList<Line>();
		if (c.moveToFirst()){
			do{
				Line temp = new Line(c.getInt(0), 
									c.getString(1), 
									c.getString(2), 
									c.getInt(3), 
									c.getString(4), 
									c.getString(5), 
									c.getString(6), 
									c.getString(7), 
									c.getFloat(8),
									TestProjectActivity.alertSubject);
				lines.add(temp);
			}while(c.moveToNext());
		}
		return lines;
	}
	

}
