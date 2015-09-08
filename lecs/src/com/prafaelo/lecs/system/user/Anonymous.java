package com.prafaelo.lecs.system.user;

import com.prafaelo.lecs.system.auth.Authenticatable;
import com.prafaelo.lecs.ui.BasicMenu;
import com.prafaelo.lecs.ui.Menu;

public class Anonymous implements Authenticatable{

	@Override
	public Menu authenticate() {
		return new BasicMenu();
	}
}
