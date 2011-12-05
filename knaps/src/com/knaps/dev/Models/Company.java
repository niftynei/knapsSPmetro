package com.knaps.dev.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {
	private int id;
	private String name;
	private int telephone;
	
	public Company(int id, String name, int telephone) {
		this.setId(id);
		this.setName(name);
		this.setTelephone(telephone);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public Company (Parcel in){
		readFromParcel(in);
	}
	
    public static final Parcelable.Creator<Parcel> CREATOR = new Parcelable.Creator() {
        public Company createFromParcel(Parcel in) {
            return new Company (in);
        }

        public Company [] newArray(int size) {
            throw new UnsupportedOperationException();
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(telephone);
}

    @SuppressWarnings("unchecked")
	private void readFromParcel(Parcel in) {
    	
       	this.id = in.readInt();
    	this.name = in.readString();
    	this.telephone = in.readInt();
}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
