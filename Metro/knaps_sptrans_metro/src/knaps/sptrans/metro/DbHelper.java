package knaps.sptrans.metro;

import android.content.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DbHelper extends SQLiteOpenHelper {

	public DbHelper(Context context) {
		super(context, "Metro", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table Lines " +
			 		 "(LineId integer primary key,"+
			 		 "Name varchar(30),"+
			 		 "Color varchar(30));";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
