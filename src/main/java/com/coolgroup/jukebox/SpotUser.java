package com.coolgroup.jukebox;

import java.io.IOException;
import java.util.concurrent.Future;

import com.google.gson.JsonParser;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.player.GetInformationAboutUsersCurrentPlaybackRequest;
import com.wrapper.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import com.wrapper.spotify.requests.data.playlists.AddTracksToPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class SpotUser extends User{

	SpotifyApi spotifyApi;
	String userID;
	com.wrapper.spotify.model_objects.specification.User spotifyUser;
	
	public SpotUser(SpotifyApi spotifyApi, ApiManager apiManager){
		
		super(apiManager);
		
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
	
	public Room createRoom() {
		
		room = new Room(this, addPlaylist());
		return room;
		
	}
	
	public int getTimeLeft() {
		
		  GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
		          spotifyApi.getInformationAboutUsersCurrentPlayback()
		                  .build();
		  try {
		      CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();

		      int timeLeft = currentlyPlayingContext.getItem().getDurationMs() - currentlyPlayingContext.getProgress_ms();
		      
		      System.out.println(timeLeft);
		      return timeLeft;
		      //System.out.println("Timestamp: " + currentlyPlayingContext.getTimestamp());
		    } catch (IOException | SpotifyWebApiException e) {
		      System.out.println("Error: " + e.getMessage());
		      return 0;
		    }
		
	}
	
	public Playlist addPlaylist() {
		
		CreatePlaylistRequest request = spotifyApi.createPlaylist(userID, "groovify_workspace").collaborative(false).public_(false).description("sup bruv").build();
		
		try {
		      Playlist playlist = request.execute();

		      System.out.println("Name: " + playlist.getName());
		      
		      return playlist;
		    } catch (Exception e) {
		      System.out.println("Error: " + e.getCause().getMessage());
		      return null;
		    }
		
	}
	public Track getTrack(String id) {
		GetTrackRequest track = spotifyApi.getTrack(id).build();
		try {
			return track.execute();
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void addTrackToPlaylist(Track track, Playlist playlist) {
		AddTracksToPlaylistRequest addTracksToPlaylistRequest = spotifyApi.addTracksToPlaylist(playlist.getId(),new String[] {track.getUri()}).position(0).build();
		
		try {
			
			addTracksToPlaylistRequest.execute();
			
		}catch(Exception e) {}
		  
	}
	
	public void startPlayBack() {
		/**
		StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
		          .startResumeUsersPlayback()
		          .context_uri(room.getPlaylist().getUri())
		          .build();
		**/
		StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
		          .startResumeUsersPlayback()
		          .uris(new JsonParser().parse("[\""+room.nextTrack().getUri()+"\"]").getAsJsonArray())
		          .build();
		try {
	        final String string = startResumeUsersPlaybackRequest.execute();

	        System.out.println("Null: " + string);
	      } catch (IOException | SpotifyWebApiException e) {
	        System.out.println("Error: " + e.getMessage());
	      }
		
	}
	
}
