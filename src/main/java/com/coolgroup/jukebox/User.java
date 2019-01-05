package com.coolgroup.jukebox;

import java.io.IOException;
import java.util.concurrent.Future;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class User {

	SpotifyApi spotifyApi;
	String userID;
	com.wrapper.spotify.model_objects.specification.User spotifyUser;
	
	public User(SpotifyApi spotifyApi){
		
		this.spotifyApi = spotifyApi;
		GetCurrentUsersProfileRequest profileRequest = spotifyApi.getCurrentUsersProfile().build();
		
		try {
			
			spotifyUser = profileRequest.execute();
			userID = spotifyUser.getId();
			
		}
		catch (IOException | SpotifyWebApiException e) {
		      System.out.println("Error: " + e.getMessage());
		}
		
	}
	public String getToken() {
		return spotifyApi.getAccessToken();
	}
	public void addPlaylist() {
		
		CreatePlaylistRequest request = spotifyApi.createPlaylist(userID, "new Playlist").collaborative(false).public_(false).description("sup bruv").build();
		
		try {
		      final Future<Playlist> playlistFuture = request.executeAsync();

		      // ...

		      final Playlist playlist = playlistFuture.get();

		      System.out.println("Name: " + playlist.getName());
		    } catch (Exception e) {
		      System.out.println("Error: " + e.getCause().getMessage());
		    }
		
	}
	
}
