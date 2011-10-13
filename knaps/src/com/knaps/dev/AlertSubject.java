package com.knaps.dev;

import java.util.ArrayList;

public class AlertSubject implements ObservationSubject {
	private ArrayList<Alert> alerts;
	private ArrayList<AlertObserver> observers;
	
	private void setAlerts(ArrayList<Alert> alerts) {
		this.alerts = alerts;
	}

	public ArrayList<Alert> getAlerts() {
		return alerts;
	}
	
	public void getCurrentAlerts(){
		//TODO: DB class to return all current Alerts
		this.setAlerts(myDB.getAllCurrentAlerts());
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
