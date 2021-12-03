package com.nostratest.crud.movieapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


import com.nostratest.crud.movieapp.model.MovieCategory;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory,Long> {
	
	Page<MovieCategory> findByMovieId(Long movieId, Pageable pageable);
	Optional<MovieCategory> findByIdAndMovieId(Long id, Long movieId);

}
