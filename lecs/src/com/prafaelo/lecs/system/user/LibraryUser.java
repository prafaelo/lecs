package com.prafaelo.lecs.system.user;

import java.util.HashMap;
import java.util.Map;

public interface LibraryUser {
	
	static final Map<Integer, LibraryUser> LIBRARYUSERS = new HashMap<Integer, LibraryUser>(); 
	
	int getLibraryCode();
	
	default void createLibraryUser(){
		LIBRARYUSERS.put(getLibraryCode(), this);
	}
}
