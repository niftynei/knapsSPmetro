package com.knaps.dev;

public interface ObservationSubject {
	public void registerObserver(AlertObserver o);
	public void removeObserver(AlertObserver o);
	public void notifyObserver();
}
