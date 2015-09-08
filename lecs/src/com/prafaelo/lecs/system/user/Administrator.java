package com.prafaelo.lecs.system.user;

import com.prafaelo.lecs.system.auth.Login;
import com.prafaelo.lecs.system.auth.Password;
import com.prafaelo.lecs.ui.LibrarianMenu;
import com.prafaelo.lecs.ui.Menu;

public class Administrator implements Login {

	private final String LOGIN_CODE = "adm";
	private final Password PASSWORD = new Password("12345678");
	
	public Administrator() {
		createLogin();
	}

	@Override
	public Menu authenticate() {
		return new LibrarianMenu();
	}

	@Override
	public void createLogin() {
		LOGINS.put("adm", this);
	}

	@Override
	public String getLoginCode() {
		return LOGIN_CODE;
	}

	@Override
	public Password getPassword() {
		return PASSWORD;
	}

}
