package com.knaps.dev.Models;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.knaps.dev.Enums.LineStatus;

public class Alert implements Parcelable {
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
	
	public Alert (Parcel in){
		readFromParcel(in);
	}
	
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Alert createFromParcel(Parcel in) {
            return new Alert (in);
        }

        public Alert [] newArray(int size) {
            throw new UnsupportedOperationException();
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
    	
        dest.writeString(message);
        dest.writeLong(timeCreated.getTime());
        dest.writeInt((status == null) ? 0 : status.index());
}

    private void readFromParcel(Parcel in) {
    	
       	this.message = in.readString();
    	this.timeCreated = new Date(in.readLong());
        this.status = LineStatus.fromInt(in.readInt());
}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
