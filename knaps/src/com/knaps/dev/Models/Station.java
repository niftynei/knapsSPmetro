package com.knaps.dev.Models;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import com.knaps.dev.Contracts.AlertObserver;
import com.knaps.dev.Contracts.ObservationSubject;
import com.knaps.dev.DAL.DataAccessor;
import com.knaps.dev.Enums.LineStatus;
import com.knaps.dev.Utilities.MyApp;
import com.knaps.dev.Utilities.statusUtility;

public class Station implements AlertObserver, Parcelable {
	private int id;
	private String displayName;
	private String mapURI;
	private boolean	bikeRack;
	private boolean bikeLoan;
	private boolean bikePark;
	private boolean elevator;
	private boolean parking;
	private long latitude;
	private long longitude;
	private ArrayList<Alert> alerts;
	private LineStatus status;
	private ArrayList<Line> lines;
	private ObservationSubject alertUpdates;
	
	public Station(int id, String displayName, String mapURI, boolean bikeRack, boolean bikeLoan, boolean bikePark, boolean elevator, boolean parking, ObservationSubject o){
		//Register for Alert Updates
		this.alertUpdates = o;
		//alertUpdates.registerObserver(this);
		
		this.id = id;
		this.displayName = displayName;
		this.mapURI = mapURI;
		this.bikeRack = bikeRack;
		this.bikeLoan = bikeLoan;
		this.bikePark = bikePark;
		this.elevator = elevator;
		this.parking = parking;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setMapURI(String mapURI) {
		this.mapURI = mapURI;
	}

	public String getMapURI() {
		return mapURI;
	}

	public void setBikeRack(boolean bikeRack) {
		this.bikeRack = bikeRack;
	}

	public boolean isBikeRack() {
		return bikeRack;
	}

	public void setBikeLoan(boolean bikeLoan) {
		this.bikeLoan = bikeLoan;
	}

	public boolean isBikeLoan() {
		return bikeLoan;
	}

	public void setBikePark(boolean bikePark) {
		this.bikePark = bikePark;
	}

	public boolean isBikePark() {
		return bikePark;
	}

	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}

	public boolean isElevator() {
		return elevator;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isParking() {
		return parking;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setAlerts(ArrayList<Alert> alerts) {
		this.alerts = alerts;
	}

	public ArrayList<Alert> getAlerts() {
		return alerts;
	}

	public void setStatus(LineStatus status) {
		this.status = status;
	}

	public LineStatus getStatus() {
		return status;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
	
	public ArrayList<Line> getLines() {
		if (lines == null){
			DataAccessor db = new DataAccessor(MyApp.getAppContext());
			setLines(db.getLinesByStation(id));	
		}
		return lines;
	}
	public void updateStatus(ObservationSubject o) {
		// TODO Auto-generated method stub
		if (o instanceof AlertSubject){
			alertUpdates = (AlertSubject)o;
			setAlerts(((AlertSubject) o).getAlerts());
			displayNewAlerts();
		}
		
	}
	public void displayNewAlerts() {
		// TODO display New Alerts
		
	}
	
	public void showMap() {
		// TODO Show station map (redirect to show map activity)
	}
	
	public boolean isOpen() {
		return statusUtility.isOpen(this);
	}
	
	public Station (Parcel in){
		readFromParcel(in);
	}
	
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Station createFromParcel(Parcel in) {
            return new Station (in);
        }

        public Station [] newArray(int size) {
            throw new UnsupportedOperationException();
        }
    };

    public void writeToParcel(Parcel dest, int flags) {

    	//private ObservationSubject alertUpdates;
    	
        dest.writeInt(id);
        dest.writeString(displayName);
        dest.writeString(mapURI);
        dest.writeInt(bikeRack ? 1 : 0);
        dest.writeInt(bikeLoan ? 1:0);
        dest.writeInt(bikePark ? 1:0);
        dest.writeInt(elevator ? 1:0);
        dest.writeInt(parking ? 1:0);
        dest.writeLong(latitude);
        dest.writeLong(longitude);
        dest.writeInt((status == null) ? 0 : status.index());
        dest.writeTypedList(alerts);
        dest.writeTypedList(lines);

}

    private void readFromParcel(Parcel in) {
    	
       	this.id = in.readInt();
    	this.displayName = in.readString();
    	this.mapURI = in.readString();
    	this.bikeRack = (in.readInt() == 0)? false: true;
    	this.bikeLoan=  (in.readInt() == 0)? false: true;
    	this.bikePark= (in.readInt() == 0)? false: true;
    	this.elevator = (in.readInt() == 0)? false: true;
    	this.parking = (in.readInt() == 0)? false: true;
    	this.latitude = in.readLong();
    	this.longitude = in.readLong();
        this.status = LineStatus.fromInt(in.readInt());
        if (alerts == null){
        	alerts = new ArrayList<Alert>();
        }
        in.readTypedList(alerts, Alert.CREATOR);
        if (lines == null){
        	lines = new ArrayList<Line>();
        }
        in.readTypedList(lines, Line.CREATOR);
}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

}
