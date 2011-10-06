package com.knaps.spmetro;

import java.util.ArrayList;

import android.app.Activity;

public abstract class Route extends Activity implements AlertObserver {
	private ObservationSubject alertUpdates;
	private ArrayList<LineSection> route;
	private ArrayList<Alert> alerts;
	private double cost;
	private String name;
	
	public Route(ObservationSubject o){
		alertUpdates = o;
		alertUpdates.registerObserver(this);
	}

	public void setAlerts(ArrayList<Alert> alerts) {
		this.alerts = alerts;
	}

	public ArrayList<Alert> getAlerts() {
		return alerts;
	}

	public void setRoute(ArrayList<LineSection> route) {
		this.route = route;
	}

	public ArrayList<LineSection> getRoute() {
		return route;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void updateStatus(ObservationSubject o) {
		// TODO Auto-generated method stub
		if (o instanceof AlertSubject){
			alertUpdates = (AlertSubject)o;
			setAlerts(((AlertSubject) o).getAlerts());
			displayNewAlerts();
		}
	}
	
	public boolean isOpen() {
		return statusUtility.isOpen(this);
	}
	
	public void share(){
		// TODO implement logic to share this to either SMS or Email
	}
	
	public void addToSavedRoutes() {
		// TODO add this route to saved routes
	}

	public void displayNewAlerts() {
		// TODO Display updated alerts
		
	}
}
