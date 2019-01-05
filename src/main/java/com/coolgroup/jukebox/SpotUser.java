package com.coolgroup.jukebox;

import java.io.IOException;
import java.util.concurrent.Future;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.playlists.AddTracksToPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class SpotUser extends User{

	SpotifyApi spotifyApi;
	String userID;
	com.wrapper.spotify.model_objects.specification.User spotifyUser;
	
	public SpotUser(SpotifyApi spotifyApi){
		
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
	@Override
	public String getToken() {
		return spotifyApi.getAccessToken();
	}
	
	public void createRoom() {
		
		room = new Room(this, addPlaylist());
		
	}
	
	public Playlist addPlaylist() {
		
		CreatePlaylistRequest request = spotifyApi.createPlaylist(userID, "new Playlist").collaborative(false).public_(false).description("sup bruv").build();
		
		try {
		      final Playlist playlist = request.execute();

		      System.out.println("Name: " + playlist.getName());
		      
		      return playlist;
		    } catch (Exception e) {
		      System.out.println("Error: " + e.getCause().getMessage());
		      return null;
		    }
		
	}
	public void addTrackToPlaylist(Track track, Playlist playlist) {
		  AddTracksToPlaylistRequest addTracksToPlaylistRequest = spotifyApi.addTracksToPlaylist(playlist.getId(),new String[] {track.getUri()}).position(0).build();
		
		
	}
	
}
