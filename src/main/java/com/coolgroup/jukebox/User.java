package com.coolgroup.jukebox;

import com.wrapper.spotify.SpotifyApi;

public class User {

	SpotifyApi spotifyApi;
	
	public User(SpotifyApi spotifyApi){
		
		this.spotifyApi = spotifyApi;
		
	}
	public String getToken() {
		return spotifyApi.getAccessToken();
	}
	
}
