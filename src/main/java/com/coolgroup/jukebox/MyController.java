package com.coolgroup.jukebox;



import java.util.ArrayList;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
@ResponseBody
@CrossOrigin
public class MyController{// implements ApplicationRunner{
	
	ArrayList<SpotUser> spotUsers = new ArrayList();
	ArrayList<User> users = new ArrayList();
	ArrayList<Room> rooms = new ArrayList();
	
	@RequestMapping(value = "/")
	public String hello() {
	   //return gameUpdater.getGame().getItems();
		
		System.out.println("lll");
		Authenticator authenticator  = new Authenticator();
		
		return authenticator.authorizationCodeUri_Async();
		
		//return "suck my pp";
	
	}
	@RequestMapping(value = "/callback")//, consumes = "text/plain" )
	public String callBack(@RequestParam("code") String code) {
		
		Authenticator authenticator = new Authenticator();
		
		SpotUser user = new SpotUser(authenticator.authorizationCode_Async(code));
		users.add(user);
		spotUsers.add(user);
		
		return user.getToken();
	}
	@RequestMapping(value = "/nonspotuser")
	public String addNonSpotUser(@RequestParam("roomkey") String key) {
		
		for(Room room : rooms) {
			
			if(room.getKey().equals(key)) {
				
				User user = new User(room);
				users.add(user);
				room.addUser(user);
				
				return user.getToken();
			}
			
		}
		
		return "";
		
	}
	
	@RequestMapping(value = "/addPlaylist")
	public String AddPlaylist(@RequestParam("token") String token) {
		
		for(SpotUser user : spotUsers) {
			if(token.equals(user.getToken())) {
				user.addPlaylist();
				//dummy change
				return "";
			}
		}
		
		return "error";
	}
	/**
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		game = new Game();
		while(true){
			game.update();
		}
	}
	**/
}