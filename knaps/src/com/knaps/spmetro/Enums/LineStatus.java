package com.knaps.spmetro.Enums;

public enum LineStatus {
	OPEN (1), 
	CLOSED (2), 
	STOPPED (3),
	REDUCED_VELOCITY (4);
	
	private final int index;   

    LineStatus(int index) {
        this.index = index;
    }

    public int index() { 
        return index; 
    }
	}
