package com.prafaelo.lecs.system.user;

import com.prafaelo.lecs.LibraryUtil;

public abstract class User {

	private String name;
	private String address;
	private String phoneNumber;

	public User() {
		create();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setNameTyped(){
		String name = LibraryUtil.getTypedValue("Enter name:");
		setName(name);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setAddressTyped(){
		String address = LibraryUtil.getTypedValue("Enter address:");
		setAddress(address);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setPhoneNumberTyped(){
		String phoneNumber = LibraryUtil.getTypedValue("Enter phone number:");
		setPhoneNumber(phoneNumber);
	}
	
	abstract void create();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=").append(name)
		.append(", address=").append(address)
		.append(", phoneNumber=")
		.append(phoneNumber).append("]");
		return builder.toString();
	}
}
