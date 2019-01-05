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
	
	ArrayList<User> users = new ArrayList();
	
	@RequestMapping(value = "/")
	public String hello() {
	   //return gameUpdater.getGame().getItems();
		
		Authenticator authenticator  = new Authenticator();
		
		return authenticator.authorizationCodeUri_Async();
		
		//return "suck my pp";
	
	}
	@RequestMapping(value = "/callback")//, consumes = "text/plain" )
	public String callBack(@RequestParam("code") String code) {
		
		Authenticator authenticator = new Authenticator();
		
		User user = new User(authenticator.authorizationCode_Async(code));
		users.add(user);
		
		return user.getToken();
	}
	@RequestMapping(value = "/addPlaylist")
	public String AddPlaylist(@RequestParam("token") String token) {
		
		for(User user : users) {
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