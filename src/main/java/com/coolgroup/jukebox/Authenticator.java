package com.coolgroup.jukebox;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;


import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Authenticator {
	private static final String clientId = "e2e54d16f0d342d6b2764b165c44fbf9";
	  private static final String clientSecret = "64e7efc989374a6e9a84a6b5ba1d39f9";
	  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://10.70.8.120:8080/main.html");

	  private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	          .setClientId(clientId)
	          .setClientSecret(clientSecret)
	          .setRedirectUri(redirectUri)
	          .build();
	  private final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
	          .state("x4xkmn9pu3j6ukrs8n")
	          .scope("user-read-birthdate,user-read-email,playlist-modify-private,streaming")
	          .show_dialog(true)
	          .build();

	  public void authorizationCodeUri_Sync() {
	    final URI uri = authorizationCodeUriRequest.execute();

	    System.out.println("URI: " + uri.toString());
	  }

	  public String authorizationCodeUri_Async() {
	    try {
	      final Future<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

	      // ...

	      final URI uri = uriFuture.get();

	      System.out.println("URI: " + uri.toString());
	      return uri.toString();
	    } catch (InterruptedException | ExecutionException e) {
	      System.out.println("Error: " + e.getCause().getMessage());
	      return "Error: " + e.getCause().getMessage();
	    }
	  }
	  public SpotifyApi authorizationCode_Async(String code) {
		  
		  AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
		  
		    try {
		      final Future<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = authorizationCodeRequest.executeAsync();

		      // ...

		      final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeCredentialsFuture.get();

		      // Set access and refresh token for further "spotifyApi" object usage
		      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
		      spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
		      
		      System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
		      
		      return spotifyApi;
		    		  
		    } catch (InterruptedException | ExecutionException e) {
		      System.out.println("Error: " + e.getCause().getMessage());
		      return null;
		    }
		  }
}
