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
	ApiManager apiManager = new ApiManager();
	
	@RequestMapping(value = "/")
	public String hello() {
	   //return gameUpdater.getGame().getItems();
		
		//System.out.println("lll");
		Authenticator authenticator  = new Authenticator();
		
		return authenticator.authorizationCodeUri_Async();
		
		//return "suck my pp";
	
	}
	@RequestMapping(value = "/callback")//, consumes = "text/plain" )
	public String callBack(@RequestParam("code") String code) {
		
		Authenticator authenticator = new Authenticator();
		
		SpotUser user = new SpotUser(authenticator.authorizationCode_Async(code), apiManager);
		users.add(user);
		spotUsers.add(user);
		
		return user.getToken();
	}
	@RequestMapping(value = "/nonspotuser")
	public String addNonSpotUser(@RequestParam("roomkey") String key) {
		
		for(Room room : rooms) {
			
			if(room.getKey().equals(key)) {
				
				User user = new User(room, apiManager);
				users.add(user);
				room.addUser(user);
				
				return user.getToken();
			}
			
		}
		
		return "";
		
	}
	
	@RequestMapping(value = "/fetchQueue")
	public String fetchQueue(@RequestParam("token") String token) {
		
		//System.out.println("I tried to blow 'im");
		
		for(User user : users) {
			
			if(user.getToken().equals(token)) {
				
				return user.fetchQueue();
				
			}
			
		}
		
		return "error";
	}
	
	@RequestMapping(value = "/createRoom")
	public String createRoom(@RequestParam("token") String token) {
		System.out.println("creating room");
		for(SpotUser user : spotUsers) {
			if(token.equals(user.getToken())) {
				Room newRoom = user.createRoom();
				rooms.add(newRoom);
				return "http://10.70.8.120:8080/main.html?roomkey=" + newRoom.getKey();
			}
		}
		
		return "error";
	}
	@RequestMapping(value = "/addSong")
	public String addSong(@RequestParam("token") String token) {
		
		for(User user : users) {
			if(token.equals(user.getToken())) {
				user.addSong();
				return "";
			}
		}
		
		return "";
	}
	
	@RequestMapping(value = "/play")
	public String play(@RequestParam("token") String token) {
		
		System.out.println("ukkiki uwu");
		for(SpotUser user : spotUsers) {
			
			if(token.equals(user.getToken())) {
				
				user.startPlayBack();
				return "";
				
			}
			
		}
		
		return "";
	}
	
}