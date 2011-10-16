package com.knaps.dev.DAL;


import java.io.*;

import com.knaps.dev.Utilities.Constants;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;
import android.content.Context;
import android.content.res.Resources;
/**
 * @author Terra_Firma
 *
 */
public class DataAccess extends SQLiteOpenHelper {
	private static final int dbVersion = Constants.DATABASE_VERSION;
	private static final String dbName = Constants.DATABASE_NAME;
	private static final String dbPath = Constants.DATABASE_PATH;
	
	private SQLiteDatabase db;
	private final Context myContext;
	private static final String TAG = "SQLiteOpenHelper";
	/**
	 * Constructor
	 * Takes and keeps a ref of the passed context in order to access
	 * the app assets and resources
	 * @param context
	 */
	public DataAccess(Context context) {
		super(context, dbName, null, dbVersion);
		this.myContext = context;
	}
	/**
	 * Creates an empty db and rewrites with my own database
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}
	public void createDataBase() throws IOException{
		boolean dbExist = checkDataBase();
		Log.d(TAG, "dbExist is" + dbExist);
		this.getReadableDatabase();
		if (dbExist){
		}else{
			try {
				copyDataBase();
			}
			catch (IOException e) {
				Log.d(TAG, "DB not copied", e);
				throw new Error("Error copying database");
			}
		}
	}
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = dbPath + dbName;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			//database doesn't exist yet
		}
		
		if(checkDB != null){
			checkDB.close();
		}
		
		Log.d(TAG, "DB Exists: " + checkDB);
		return checkDB != null ? true: false;
	}
	private void copyDataBase() throws IOException {
		InputStream myInput = myContext.getAssets().open(dbName);
		String outFileName = dbPath + dbName;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		
		Log.d(TAG, "DB Writing finished");
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	public void openDataBase() throws SQLException{
		String myPath = dbPath + dbName;
		db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}
	@Override 
	public synchronized void close(){
		if(db != null){
			db.close();
			super.close();
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
