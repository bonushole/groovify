package com.coolgroup.jukebox;



import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

@Controller
@ResponseBody
@CrossOrigin
public class MyController{// implements ApplicationRunner{
	
	@RequestMapping(value = "/")
	public String hello() {
	   //return gameUpdater.getGame().getItems();
		//SpotifyApi spotapi = new SpotifyApi;
		return "suck my pp";
	
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
