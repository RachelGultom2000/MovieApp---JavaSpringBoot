package com.nostratest.crud.movieapp.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;



import com.nostratest.crud.movieapp.model.Movie;
import com.nostratest.crud.movieapp.repository.MovieRepository;
import com.nostratest.crud.movieapp.service.MovieService;

public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(rollbackOn = {RuntimeException.class})
	public void saveMovie(Movie movie) {
		Movie movie1 = null;
		// save movie
		movie1 = movieRepository.save(movie);
	}
	
}
