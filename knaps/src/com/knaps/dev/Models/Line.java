package com.knaps.dev.Models;

import java.util.ArrayList;

import android.app.Activity;

import com.knaps.dev.Contracts.AlertObserver;
import com.knaps.dev.Contracts.ObservationSubject;
import com.knaps.dev.DAL.DataHelper;
import com.knaps.dev.Enums.LineStatus;
import com.knaps.dev.Enums.SystemType;
import com.knaps.dev.Utilities.statusUtility;

public class Line extends Activity implements AlertObserver {
	private int id;
	private String displayName;
	private int number;
	private String color;
	private Company company;
	private String iconURI;
	private String imgURI;
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
		this.iconURI = iconURI;
		this.imgURI = imgURI;
		this.fare = fare;
		
		//Register line with the alert updater
		AlertUpdater = o;
		//AlertUpdater.registerObserver(this);
	}

	private void setStations(int lineId) {
		 stations = new DataHelper(this.getApplicationContext()).getStationsByLine(lineId);
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
		return iconURI;
	}
	public String getImg(){
		return imgURI;
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
}
