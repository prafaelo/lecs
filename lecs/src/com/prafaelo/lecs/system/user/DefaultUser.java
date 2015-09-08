package com.prafaelo.lecs.system.user;

import com.prafaelo.lecs.LibraryUtil;

public class DefaultUser extends User implements LibraryUser {

	private int libraryCode;
	
	public DefaultUser() {
	}

	@Override
	void create() {
		setNameTyped();
		setAddressTyped();
		setPhoneNumberTyped();
		setLibraryCodeTyped();
		createLibraryUser();
	}

	@Override
	public int getLibraryCode() {
		return libraryCode;
	}
	
	private void setLibraryCode(int libraryCode) {
		this.libraryCode = libraryCode;
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
