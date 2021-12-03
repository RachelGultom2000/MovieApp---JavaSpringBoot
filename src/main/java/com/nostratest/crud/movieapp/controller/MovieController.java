package com.nostratest.crud.movieapp.controller;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nostratest.crud.movieapp.exception.ResourceNotFoundException;
import com.nostratest.crud.movieapp.model.Movie;
import com.nostratest.crud.movieapp.repository.MovieRepository;


@RestController
public class MovieController {


	@Autowired
	private MovieRepository movieRepository;
	
	public MovieController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@GetMapping("/movies")
	  public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title) {
	    try {
	      List<Movie> movies = new ArrayList<Movie>();

	      if (title == null)
	        movieRepository.findAll().forEach(movies::add);
	      else
	    	  movieRepository.findByTitleContaining(title).forEach(movies::add);

	      if (movies.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(movies, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable (value = "id") Long id)
		throws ResourceNotFoundException{
			Movie movie = movieRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Movie not found on :" + id));
			return  ResponseEntity.ok().body(movie);
		}

	
	@PostMapping("/movies")
	public Movie createMovie(@Valid @RequestBody Movie movie) {
		return movieRepository.save(movie);
	}
	
	@PutMapping("/movies/{movieId}")
	public Movie updateMovie(@PathVariable Long movieId, @Valid @RequestBody Movie movieReqeust) {
		return movieRepository.findById(movieId).map(movie -> {
			movie.setTitle(movieReqeust.getTitle());
			movie.setDescription(movieReqeust.getDescription());
			movie.setDuration(movieReqeust.getDuration());
			movie.setRating(movieReqeust.getRating());
			movie.setImage(movieReqeust.getImage());
//			movie.setReleaseYear(movieReqeust.getReleaseYear());
			return movieRepository.save(movie);
		}).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
	}
	
	@DeleteMapping("/movies/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable Long movieId){
		return movieRepository.findById(movieId).map(movie -> {
			movieRepository.delete(movie);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
	}
	
	
	
}
