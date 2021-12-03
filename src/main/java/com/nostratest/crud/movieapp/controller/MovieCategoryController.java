package com.nostratest.crud.movieapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nostratest.crud.movieapp.model.MovieCategory;
import com.nostratest.crud.movieapp.exception.ResourceNotFoundException;
import com.nostratest.crud.movieapp.repository.MovieCategoryRepository;
import com.nostratest.crud.movieapp.repository.MovieRepository;

@RestController
public class MovieCategoryController {

	@Autowired
	private MovieCategoryRepository moviecategoryRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@GetMapping("/movies/{movieId}/categories")
	public Page<MovieCategory> getAllMoviesCategoryByMovieId(@PathVariable (value = "movieId") Long movieId, Pageable pageable){
		return moviecategoryRepository.findByMovieId(movieId, pageable);
	}
	
	@PostMapping("/movies/{movieId}/categories")
	  public MovieCategory createmoviecategory(@PathVariable (value = "movieId") Long movieId,
              @Valid @RequestBody MovieCategory moviecategory) {
		return movieRepository.findById(movieId).map(movie -> {
			moviecategory.setMovie(movie);
			return moviecategoryRepository.save(moviecategory);
		}).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
	}
	
	@PutMapping("/movies/{movieId}/categories/{categoriesId}")
	public MovieCategory updatemoviecategory(@PathVariable (value = "movieId") Long movieId,
            @PathVariable (value = "moviecategoryId") Long moviecategoryId,
            @Valid @RequestBody MovieCategory moviecategoryRequest) {
		if(!movieRepository.existsById(movieId)) {
			throw new ResourceNotFoundException("MovieId " + movieId + " not found");
		}

		return moviecategoryRepository.findById(movieId).map(moviecategory -> {
			moviecategory.setName(moviecategoryRequest.getName());
			return moviecategoryRepository.save(moviecategory);
		}).orElseThrow(() -> new ResourceNotFoundException("MovieCategoryId " + moviecategoryId + "not found"));
	}
	
		@DeleteMapping("/movies/{movie}/categories/{categoriesId}")
		public ResponseEntity<?> deleteMovieCategory(@PathVariable (value = "movieId") Long movieId,
				@PathVariable(value = "moviecategoryId") Long moviecategoryId){
				return moviecategoryRepository.findByIdAndMovieId(moviecategoryId, movieId).map(moviecategory -> {
					moviecategoryRepository.delete(moviecategory);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + moviecategoryId + " and movieId" + movieId));
		}		
	}

