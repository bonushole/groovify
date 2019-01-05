package com.coolgroup.jukebox;

import com.wrapper.spotify.model_objects.specification.Track;

public class User {

	String token;
	Room room;
	ApiManager apiManager;
	
	public User() {}
	
	public User(Room room, ApiManager apiManager) {
		
		this.room = room;
		this.apiManager = apiManager;
		
	}
	
	public String getToken() {
		
		return "";
	}
	public void addSong() {
		
		Track song = apiManager.getTrack((Math.random()>.5)? "spotify:track:5QIbR39hAEDIOkr4ggh4xc":"spotify:track:3WibbMr6canxRJXhNtAvLU");
		room.addTrack(song);
	}
	
}
