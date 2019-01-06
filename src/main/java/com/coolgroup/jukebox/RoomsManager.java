package com.coolgroup.jukebox;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class RoomsManager {

	ArrayList<Room> rooms;
	
	public RoomsManager() {
		
		rooms = new ArrayList<Room>();
		
	}
	public  ArrayList<Room> getRooms(){
		return rooms;
	}
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
}
