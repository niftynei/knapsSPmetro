package com.knaps.dev.Models;

import java.util.Date;

import com.knaps.dev.Enums.LineStatus;

public class Alert {
	private String message;
	private Date timeCreated;
	private LineStatus status;
	
	public Alert(String message, Date timeCreated, LineStatus status) {
		this.message = message;
		this.timeCreated = timeCreated;
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setStatus(LineStatus status) {
		this.status = status;
	}

	public LineStatus getStatus() {
		return status;
	}
	
}
