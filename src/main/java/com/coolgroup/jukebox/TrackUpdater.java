package com.coolgroup.jukebox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TrackUpdater {

	@Autowired
	RoomsManager roomManager;
	
	@Scheduled(fixedRate=1000)
	public void upDateTracks() {
		
		//System.out.println("aki give me tendies");
		
		for(Room room : roomManager.getRooms()) {
			
			room.checkIfSongOver();
			
		}
		
	}
	
}
