package com.knaps.spmetro;

public interface ObservationSubject {
	public void registerObserver(AlertObserver o);
	public void removeObserver(AlertObserver o);
	public void notifyObserver();
}
