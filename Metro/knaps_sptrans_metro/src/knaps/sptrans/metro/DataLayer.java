package knaps.sptrans.metro;

import android.database.sqlite.SQLiteDatabase;
import android.content.*;

public class DataLayer {
	private DbHelper _dbHelper;
	
	public DataLayer(Context c){
		_dbHelper = new DbHelper(c);
	}
	public void AddLine(String name, String color){
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("Name", name);
			values.put("Color", color);
			
			db.insert("Lines", "", values);
		}
		finally{
			if (db != null){
				db.close();
			}
		}
	}
}
