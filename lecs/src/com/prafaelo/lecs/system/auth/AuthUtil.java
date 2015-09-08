package com.prafaelo.lecs.system.auth;

import com.prafaelo.lecs.LibraryUtil;
import com.prafaelo.lecs.system.user.Anonymous;

public class AuthUtil {
	
	private AuthUtil() {
	}

	public static Authenticatable processLogin(){
		Authenticatable authenticatable=new Anonymous();
			
		String userLogin = LibraryUtil.getTypedValue("Enter login:");
		String password = LibraryUtil.getTypedValue("Enter password:");
	
		Login login = Login.LOGINS.get(userLogin);
		if(login == null){
			System.err.println("(Error) Login not found!");
		} else {
			if(login.getPassword().equals(new Password(password))){
				return login;
			} else {
				System.err.println("(Error) Invalid Password!");				
			}
		}
		return authenticatable;
	}
}
