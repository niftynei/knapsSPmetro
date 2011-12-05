package com.knaps.dev.Enums;

public enum SystemType {
		METRO (1), BUS (2);
		
		private final int index;   

	    SystemType(int index) {
	        this.index = index;
	    }

	    public int index() { 
	        return index; 
	    }
	    public static SystemType fromInt(int key) {
	          for (SystemType b : SystemType.values()) {
	            if (key == b.index) {
	              return b;
	            }
	          }
	        return null;
	    }
}
