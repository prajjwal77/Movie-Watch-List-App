package com.example.prajjwal.watchlistApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prajjwal.watchlistApp.entity.Movie;
import com.example.prajjwal.watchlistApp.repository.MovieRepository;

@Service
public class DatabaseService {

	@Autowired
	public MovieRepository movieRepo;
	
	@Autowired
	RatingService ratingService;
	
	
	
	public void create(Movie movie) {
		String rating = ratingService.getMovieRating(movie.getTitle());
		if(rating!=null) {
			movie.setRating(Float.parseFloat(rating));
		}
		movieRepo.save(movie);
	}
	
	public List<Movie> getAllMovies() {
		
		return movieRepo.findAll();
	}
	
	public Movie getMovieById(Integer id) {
		return movieRepo.findById(id).get();
	}

	public void update(Movie movie, Integer id) {
		
		Movie toBeUpdated = getMovieById(id);
		toBeUpdated.setTitle(movie.getTitle());
		toBeUpdated.setRating(movie.getRating());
		toBeUpdated.setComment(movie.getComment());
		toBeUpdated.setPriority(movie.getPriority());
		
		movieRepo.save(toBeUpdated);
	}
	
	public void delete(Integer id) {
           
		movieRepo.deleteById(id);
 
	}	
}
