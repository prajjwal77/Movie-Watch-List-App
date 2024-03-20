package com.example.prajjwal.watchlistApp.service;

import java.security.PublicKey;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
	  
	String apiUrl = "https://www.omdbapi.com/?apikey=a8431cee&t=";

		public String  getMovieRating(String title) {
		try {
			//try fetch the rating by calling omdb api
			RestTemplate template = new RestTemplate();//programatically  api that call  external api
			//calling apiUrl+title
			ResponseEntity<ObjectNode>responseEntity=template.getForEntity(apiUrl + title, ObjectNode.class);
			
			ObjectNode jsonObjectNode = responseEntity.getBody();
			
			return jsonObjectNode.path("imdbRating").asText();
		}catch (Exception e) {
			// to user entered rating will be taken
			System.out.println("Either movie name is Not Available"+ e.getMessage());
			return null;
		}
	}
}
