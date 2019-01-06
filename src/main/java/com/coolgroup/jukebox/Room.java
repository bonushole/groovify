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
		key = KeyGenerator.generateKey();
		
	}
	
	public String getKey() {
		
		return key;
		
	}
	public Playlist getPlaylist() {
		
		return playlist;
		
	}
	public void addUser(User user) {
		
		users.add(user);
		
	}
	public void addTrack(Track track) {
		
		tracks.add(track);
		owner.addTrackToPlaylist(track, playlist);
		
	}
	public Track getTrack(String id) {
		return owner.getTrack(id);
	}
	public void updatePlaylist() {
		
		
		
	}
	public ArrayList<SongsForGson> fetchSongs() {
		
		ArrayList<SongsForGson> elements = new ArrayList<SongsForGson>();
		
		for (Track track : tracks) {
			
			elements.add(new SongsForGson(track.getName()));
		}
		return elements;
		
	}
	
	
}
