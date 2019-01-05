package com.coolgroup.jukebox;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Track;

public class ApiManager {

	SpotifyApi spotifyApi;
	
	public ApiManager() {
		spotifyApi = new SpotifyApi.Builder().setClientId("e2e54d16f0d342d6b2764b165c44fbf9").setClientSecret("64e7efc989374a6e9a84a6b5ba1d39f9").build();
	}
	public Track getTrack() {
		//Track 
 	}
	
}
