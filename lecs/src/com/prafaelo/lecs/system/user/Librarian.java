package com.prafaelo.lecs.system.user;

import com.prafaelo.lecs.LibraryUtil;
import com.prafaelo.lecs.system.auth.Login;
import com.prafaelo.lecs.system.auth.Password;
import com.prafaelo.lecs.ui.LibrarianMenu;
import com.prafaelo.lecs.ui.Menu;

public class Librarian extends User implements Login, LibraryUser{
	
	private String loginCode;
	private Password loginPassword;
	
	private int libraryCode;
	
	public Librarian() {
		createLogin();
		createLibraryUser();
		System.out.println("Librarian account sucessfully created:");
		System.out.println(this);
	}
	
	@Override
	public Menu authenticate() {
		return new LibrarianMenu();
	}

	@Override
	public void createLogin() {
		setLoginCodeTyped();
		setLoginPasswordTyped();
		LOGINS.put(getLoginCode(), this);
	}

	@Override
	public String getLoginCode() {
		return loginCode;
	}
	
	private void setLoginCodeTyped(){
		String loginCodeTyped = LibraryUtil.getTypedValue("Enter login:");
		setLoginCode(loginCodeTyped);
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

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	@Override
	public Password getPassword() {
		return this.loginPassword;
	}
	
	public void setLoginPassword(Password loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setLoginPasswordTyped(){
		String loginPasswordTyped = LibraryUtil.getTypedValue("Enter password:");		
		if(loginPasswordTyped.length()>8){
			System.err.println("Password too long! The length must be 8 chars!");
		} else if (loginPasswordTyped.length()<7){
			System.err.println("Password too short! The length must be 8 chars!");			
		}
		setLoginPassword(new Password(loginPasswordTyped));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + libraryCode;
		result = prime * result + ((loginCode == null) ? 0 : loginCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Librarian other = (Librarian) obj;
		if (libraryCode != other.libraryCode)
			return false;
		if (loginCode == null) {
			if (other.loginCode != null)
				return false;
		} else if (!loginCode.equalsIgnoreCase(other.loginCode))
			return false;
		return true;
	}

	@Override
	void create() {
		setNameTyped();
		setAddressTyped();
		setPhoneNumberTyped();
		setLibraryCodeTyped();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Librarian [loginCode=").append(loginCode)
		.append(", loginPassword=").append(loginPassword)
		.append(", libraryCode=").append(libraryCode).append("]")
		.append(", ").append(super.toString());
		return builder.toString();
	}
}
