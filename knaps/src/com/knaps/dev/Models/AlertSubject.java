package com.knaps.dev.Models;

import java.util.ArrayList;

import com.knaps.dev.Contracts.AlertObserver;
import com.knaps.dev.Contracts.ObservationSubject;
import com.knaps.dev.DAL.DataAccessor;

import android.app.Activity;

public class AlertSubject extends Activity implements ObservationSubject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3807085702484825483L;
	private ArrayList<Alert> alerts;
	private ArrayList<AlertObserver> observers;
	private static AlertSubject alertsub = new AlertSubject();
	
	public AlertSubject(){
		observers = new ArrayList<AlertObserver>();
	}
	
	public static AlertSubject getAlertSubject(){
		return alertsub;
	}
	
	
	private void setAlerts(ArrayList<Alert> alerts) {
		this.alerts = alerts;
	}

	public ArrayList<Alert> getAlerts() {
		return alerts;
	}
	
	public void getCurrentAlerts(){
		//TODO: DB class to return all current Alerts
		DataAccessor db = new DataAccessor(this.getApplicationContext());
		this.setAlerts(db.getAllCurrentAlerts());
		notifyObserver();
	}


	public void registerObserver(AlertObserver o) {
		observers.add(o);
	}


	public void removeObserver(AlertObserver o) {
		int i = observers.indexOf(o);
		if (i >= 0){
			observers.remove(o);	
		}
	}

	public void notifyObserver() {
		for (int i = 0; i <observers.size(); i++){
			AlertObserver observer = (AlertObserver)observers.get(i);
			observer.updateStatus(this);
		}
	}
	
	
	
}
