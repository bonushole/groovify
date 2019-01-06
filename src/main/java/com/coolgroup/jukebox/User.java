package com.coolgroup.jukebox;

import com.google.gson.Gson;
import com.wrapper.spotify.model_objects.specification.Track;

public class User {

	String token;
	Room room;
	ApiManager apiManager;
	
	public User(ApiManager apiManager) {
		this.apiManager = apiManager;
	}
	
	public User(Room room, ApiManager apiManager) {
		
		this.room = room;
		this.apiManager = apiManager;
		token = KeyGenerator.generateKey();
		
	}
	
	public String getToken() {
		
		return token;
	}
	
	public void addSong() {
		
		//System.out.println("we made it here");
		Track song = room.getTrack((Math.random()>.5)? "5QIbR39hAEDIOkr4ggh4xc":"3WibbMr6canxRJXhNtAvLU");
		System.out.println(song);
		room.addTrack(song);
	}
	public void addSong(String songId) {
		
		//System.out.println("we made it here");
		Track song = room.getTrack(songId);
		System.out.println(song);
		room.addTrack(song);
	}
	public String fetchQueue() {
		
		Gson gson = new Gson();
		return gson.toJson(room.fetchSongs());
		
	}
	public String searchSongs(String query) {
		
		Gson gson = new Gson();
		return gson.toJson(room.searchTracks(query));
		
	}
	
}
