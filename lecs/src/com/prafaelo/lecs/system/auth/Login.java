package com.prafaelo.lecs.system.auth;

import java.util.HashMap;
import java.util.Map;

public interface Login extends Authenticatable {
	
	static final Map<String, Login> LOGINS = new HashMap<String, Login>();
	
	void createLogin();
	
	String getLoginCode();
	
	Password getPassword();
}
