package com.knaps.dev.Utilities;

public class Constants {
	public static final String SHARED_PREFS = "SHARED_PREFS";
	public static final String DATABASE_NAME ="Metro.db";
	public static final int DATABASE_VERSION=1;
	public static final String DATABASE_PATH="/data/data/com.knaps.dev/databases/";
	public static final String DB_ALERT = "alert";
	public static final String DB_ALERTLINEHOUR = "alertlinehour";
	public static final String DB_DROID_METADATA = "android_metadata";
	public static final String DB_COMPANY = "company";
	public static final String DB_DAY = "day";
	public static final String DB_LINE = "line";
	public static final String DB_LINEHOUR = "linehour";
	public static final String DB_LINESTATUS = "linestatus";
	public static final String DB_STATION = "station";
	public static final String DB_STATIONLINE = "stationline";
	public static final String DB_ALERT_FIELDS = "a.Message, a.Created, a.StatusId";
	public static final String DB_LINE_FIELDS = "l._id" + 
												", l.name" +
												", l.displayName" +
												", l.number" +
												", l.color" +
												", l.name" +
												", l.iconURL"+
												", l.imgURL"+
												", l.tarifa";
	public static final String DB_STATION_FIELDS = "s._id" +
													", s.name" +
													", s.displayName" +
													", s.mapURL" +
													", s.bikeRack" +
													", s.bikePark" +
													", s.bikeLoan" +
													", s.elevator" +
													", s.parking";
	public static final String ALERT_SUBJECT = "alert_subject";
	

}
