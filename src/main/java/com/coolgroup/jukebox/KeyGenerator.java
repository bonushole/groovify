package com.coolgroup.jukebox;

import java.util.Random;

public class KeyGenerator {

	public static String generateKey() {
		
	       java.lang.String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ";
	        java.lang.String randomString = "";

	        int length = 25;

	        Random rand = new Random();

	        char[] text = new char[length];

	        for(int i = 0; i<length; i++) {
	            text[i] = characters.charAt(rand.nextInt(characters.length()));
	        }

	        for(int i = 0; i < text.length; i++) {
	            randomString = randomString + text[i];
	        }

	        return randomString;
		
	}
	
}
