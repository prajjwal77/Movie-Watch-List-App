package com.example.prajjwal.watchlistApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.prajjwal.watchlistApp.entity.Movie;
import com.example.prajjwal.watchlistApp.service.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {
	
	@Autowired
	  DatabaseService databaseService;
	
	@GetMapping("/whatchlistItemForm")
	public ModelAndView showWhatchListForm(@RequestParam(required = false) Integer id) {
		
		String viewName="whatchlistItemForm";
		Map<String,Object>model = new HashMap<>();
		
		//Movie movie  = new Movie();
		
		if(id==null) {
			model.put("whatchlistItem",new Movie());
		}
		else {
			model.put("whatchlistItem", databaseService.getMovieById(id));
		}
		
		
	return new ModelAndView(viewName,model);
	}
	
	@PostMapping("/whatchlistItemForm")
	public ModelAndView submitWatchListForm(@Valid @ModelAttribute("whatchlistItem") Movie movie ,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			//if errors are there, redisplay the form and let user enter again
			return new ModelAndView("/whatchlistItemForm");
			
		}
		
		Integer id = movie.getId();
		if(id==null) {
			databaseService.create(movie);
		}
		else {
			databaseService.update(movie,id);
		}
				RedirectView rd = new RedirectView();
		rd.setUrl("/whatchlist");
		
		return new ModelAndView(rd);
	}
	
	@GetMapping("/whatchlist")
	public ModelAndView getWhatchlist() {
		String viewName = "whatchlist";
		Map<String, Object> model = new HashMap<>();
		List<Movie> movieList=databaseService.getAllMovies();
		model.put("whatchlistrows",movieList );
		model.put("noofmovies", movieList.size());
		return  new ModelAndView(viewName,model);
		
	}
	
	@GetMapping("/deleteMovie/{id}")
	public String deleteById(@PathVariable(value="id")Integer id){
	        databaseService.delete(id);
	         return "whatchlist:/";
	}

}
