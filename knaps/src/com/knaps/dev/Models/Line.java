package com.knaps.dev.Models;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import com.knaps.dev.Contracts.AlertObserver;
import com.knaps.dev.Contracts.ObservationSubject;
import com.knaps.dev.DAL.DataAccessor;
import com.knaps.dev.Enums.LineStatus;
import com.knaps.dev.Enums.SystemType;
import com.knaps.dev.Utilities.MyApp;
import com.knaps.dev.Utilities.statusUtility;

public class Line implements AlertObserver, Parcelable {
	private int id;
	private String displayName;
	private int number;
	private String color;
	private Company company;
	private String iconUri;
	private String imgUri;
	private double fare;
	private ArrayList<Station> stations;
	private ArrayList<Alert> alerts;
	private LineStatus status;
	private SystemType systemType;
	private ObservationSubject AlertUpdater;
	
	public Line(int lineId, String displayName, int number, ObservationSubject o){
		id = lineId;
		this.displayName = displayName;
		this.number = number;
		setStations(lineId);
		
		//Register Line with the Alert Updater
		AlertUpdater = o;
		AlertUpdater.registerObserver(this);		
	}
	
	public Line(int id, String name, String displayName, int number,
			String color, String company, String iconURI, String imgURI,
			float fare, ObservationSubject o) {
		this.id = id;
		this.displayName = displayName;
		this.number = number;
		this.color = color;
		//TODO: Wire up company to pull via COMPANY ID
		this.company = null;
		this.iconUri = iconURI;
		this.imgUri = imgURI;
		this.fare = fare;
		
		//Register line with the alert updater
		AlertUpdater = o;
		//AlertUpdater.registerObserver(this);
	}

	private void setStations(int lineId) {
		 stations = new DataAccessor(MyApp.getAppContext()).getStationsByLine(lineId);
	}
	public void setStations(ArrayList<Station> stations){
		this.stations = stations;
	}
	public ArrayList<Station> getStations() {
		return this.stations;
	}
	
	public ArrayList<Station> getLineSection(Station start, Station end){
		// TODO: Check that Stations Exist on this line
		
		// TODO: Return shortened list of Stations 
		
		return null;
	}

	public int getId() {
		return id;
	}
	public String getDisplayName(){
		return displayName;
	}
	public int getNumber(){
		return number;
	}
	public String getColor(){
		return color;
	}
	public Company getCompany(){
		return company;
	}
	public String getIcon(){
		return iconUri;
	}
	public String getImg(){
		return imgUri;
	}
	public double getTarifa(){
		return fare;
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

	public void setSystemType(SystemType systemType) {
		this.systemType = systemType;
	}

	public SystemType getSystemType() {
		return systemType;
	}

	public void updateStatus(ObservationSubject o) {
		// TODO Auto-generated method stub
		if (o instanceof AlertSubject){
			AlertUpdater = (AlertSubject)o;
			setAlerts(((AlertSubject) o).getAlerts());
			displayNewAlerts();
		}
	}

	public void displayNewAlerts() {
		// TODO Display new alerts info
		
	}
	
	public boolean isOpen(){
		// Checks that this station is open
		return statusUtility.isOpen(this);
	}
	
	public Line (Parcel in){
		readFromParcel(in);
	}
	
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Line createFromParcel(Parcel in) {
            return new Line (in);
        }

        public Line [] newArray(int size) {
            throw new UnsupportedOperationException();
        }
    };

    public void writeToParcel(Parcel dest, int flags) {

//    	private ObservationSubject AlertUpdater;
    	
        dest.writeInt(id);
        dest.writeString(displayName);
        dest.writeInt(number);
        dest.writeString(color);
        dest.writeParcelable(company, flags);
        dest.writeString(iconUri);
        dest.writeString(imgUri);
        dest.writeDouble(fare);
        dest.writeTypedList(stations);
        dest.writeTypedList(alerts);
        dest.writeInt((status == null) ? 0 : status.index());
        dest.writeInt((systemType == null) ? 0 : systemType.index());

}

    @SuppressWarnings("unchecked")
	private void readFromParcel(Parcel in) {
    	
       	this.id = in.readInt();
    	this.displayName = in.readString();
    	this.number = in.readInt();
    	this.color = in.readString();
    	this.company = in.readParcelable(Company.class.getClassLoader());
    	this.iconUri=  in.readString();
    	this.imgUri= in.readString();
    	this.fare = in.readDouble();
        if (stations == null) {
            stations = new ArrayList<Station>();
        }
        in.readTypedList(stations, Station.CREATOR);
        if (alerts == null){
        	alerts = new ArrayList<Alert>();
        }
        in.readTypedList(alerts, Alert.CREATOR);
        this.status = LineStatus.fromInt(in.readInt());
        this.systemType = SystemType.fromInt(in.readInt());
}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
