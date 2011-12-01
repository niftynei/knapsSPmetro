package com.knaps.dev.Utilities;

import java.util.Date;

import com.knaps.dev.Contracts.ObservationSubject;
import com.knaps.dev.Contracts.Route;
import com.knaps.dev.Models.CalculatedRoute;
import com.knaps.dev.Models.PlannedRoute;
import com.knaps.dev.Models.Station;

import android.app.Activity;

public final class RouteCreator extends Activity {
	public PlannedRoute findRoute(Station startStation, Station endStation){
		return (PlannedRoute)calculateRoute(startStation, endStation);
	}

	public CalculatedRoute findRoute(Station startStation, Station endStation, Date time, boolean isStart){
		return calcDuration(calculateRoute(startStation, endStation), time, isStart);
	}
	
	private CalculatedRoute calcDuration(Route route, Date time, boolean isStart) {
			// TODO calculation of duration of Route!
			((CalculatedRoute) route).setStartTime(null);
			((CalculatedRoute) route).setEndTime(null);
			return (CalculatedRoute)route;
	}

	private Route calculateRoute(Station startStation, Station endStation) {
		// TODO Calculate route logix!
		
		Route route = new PlannedRoute((ObservationSubject)AppPrefs.getAppPrefs().getSerializable(Constants.ALERT_SUBJECT));
		
		route.setCost(calculateCost());
		return null;
	}
	
	private double calculateCost(){
		// TODO calculate the cost of a route! 
		return 0;
		
	}
}
