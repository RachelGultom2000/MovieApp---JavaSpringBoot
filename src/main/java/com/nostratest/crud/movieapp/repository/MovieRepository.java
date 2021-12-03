package com.nostratest.crud.movieapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nostratest.crud.movieapp.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{

	List<Movie> findByTitle(boolean title);
	List<Movie> findByTitleContaining(String title);
}
