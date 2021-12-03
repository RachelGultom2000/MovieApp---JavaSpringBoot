package com.nostratest.crud.movieapp.service;

import org.springframework.stereotype.Component;

import com.nostratest.crud.movieapp.model.Movie;

@Component
public interface MovieService {

	public void saveMovie(Movie movie);
	
}
