package com.coolgroup.jukebox;

import java.util.ArrayList;

import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;

public class Room {

	SpotUser owner;
	String key;
	ArrayList<User> users = new ArrayList<User>();
	Playlist playlist;
	ArrayList<Track> tracks = new ArrayList<Track>();
	
	public Room(SpotUser owner, Playlist playlist) {
		
		this.owner = owner;
		this.playlist = playlist;
		
	}
	
	public String getKey() {
		
		return key;
		
	}
	public void addUser(User user) {
		
		users.add(user);
		
	}
	public void addTrack(Track track) {
		
		tracks.add(track);
		owner.addTrackToPlaylist(track, playlist);
		
	}
	public void updatePlaylist() {
		
		
		
	}
	
	
}
