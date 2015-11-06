package com.prafaelo.lecs.system.user;

import com.prafaelo.lecs.LibraryUtil;

public class DefaultUser extends User implements LibraryUser {

	private int libraryCode;
	
	public DefaultUser() {
		createTyped();
	}
	
	public DefaultUser(String name, String address, String phoneNumber){
		create(name, address, phoneNumber);
	}

	void createTyped() {
		setNameTyped();
		setAddressTyped();
		setPhoneNumberTyped();
		//setLibraryCodeTyped();
		setLibraryCode(generateLibraryCode());
		createLibraryUser();
	}
	
	void create(String name, String address, String phoneNumber){
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNumber);
		setLibraryCode(generateLibraryCode());
		createLibraryUser();
	}

	@Override
	public int getLibraryCode() {
		return libraryCode;
	}
	
	private void setLibraryCode(int libraryCode) {
		this.libraryCode = libraryCode;
	}
	
	private int generateLibraryCode(){
		int codeGenerate = LibraryUser.LIBRARYUSERS.size() + 1;
		return codeGenerate;
	}
	
	public void setLibraryCodeTyped(){
		String libraryCode = LibraryUtil.getTypedValue("Enter library code:");
		setLibraryCode(Integer.parseInt(libraryCode));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DefaultUser [libraryCode=").append(libraryCode).append("]")
		.append(", ").append(super.toString());
		return builder.toString();
	}
}
