package com.knaps.dev;

public class Company {
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
}
