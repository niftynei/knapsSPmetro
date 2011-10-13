package com.knaps.spmetro;

import java.util.ArrayList;

import com.knaps.spmetro.Enums.LineStatus;
import com.knaps.spmetro.Enums.SystemType;

public class Line implements AlertObserver {
	private int _id;
	private String _displayName;
	private int _number;
	private String _color;
	private Company _company;
	private String _iconURI;
	private String _imgURI;
	private double _fare;
	private ArrayList<Station> stations;
	private ArrayList<Alert> alerts;
	private LineStatus status;
	private SystemType systemType;
	private ObservationSubject AlertUpdater;
	
	public Line(int lineId, String displayName, int number, ObservationSubject o){
		_id = lineId;
		_displayName = displayName;
		_number = number;
		setStations(lineId);
		
		//Register Line with the Alert Updater
		AlertUpdater = o;
		AlertUpdater.registerObserver(this);		
	}
	
	private void setStations(int lineId) {
		 stations = myDB.getStationsByLine(lineId);
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
		return _id;
	}
	public String getDisplayName(){
		return _displayName;
	}
	public int getNumber(){
		return _number;
	}
	public String getColor(){
		return _color;
	}
	public Company getCompany(){
		return _company;
	}
	public String getIcon(){
		return _iconURI;
	}
	public String getImg(){
		return _imgURI;
	}
	public double getTarifa(){
		return _fare;
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

	@Override
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
