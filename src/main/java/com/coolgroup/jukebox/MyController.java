package com.coolgroup.jukebox;



import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
@ResponseBody
@CrossOrigin
public class MyController{// implements ApplicationRunner{
	
	@RequestMapping(value = "/")
	public String hello() {
	   //return gameUpdater.getGame().getItems();
		
		Authenticator authenticator  = new Authenticator();
		
		return authenticator.authorizationCodeUri_Async();
		
		//return "suck my pp";
	
	}
	@RequestMapping(value = "/callback")//, consumes = "text/plain" )
	public String callBack() {//@RequestBody String payload) {
		System.out.println("Something happened");
		return "something happened";
		//return payload;
		
		//Authenticator authenticator = new Authenticator();
		
		//return "";
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