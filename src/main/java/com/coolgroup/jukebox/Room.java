package com.coolgroup.jukebox;

import java.util.ArrayList;

public class Room {

	SpotUser owner;
	String key;
	ArrayList<User> users = new ArrayList();
	
	public String getKey() {
		
		return key;
		
	}
	public void addUser(User user) {
		
		users.add(user);
		
	}
	
	
}
