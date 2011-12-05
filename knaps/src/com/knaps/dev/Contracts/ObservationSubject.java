package com.knaps.dev.Contracts;

import java.io.Serializable;

public interface ObservationSubject extends Serializable {
	public void registerObserver(AlertObserver o);
	public void removeObserver(AlertObserver o);
	public void notifyObserver();
}
