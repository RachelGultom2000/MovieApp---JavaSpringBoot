package com.nostratest.crud.movieapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "movies")
public class Movie extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String title;
	
	@NotNull
	@Size(max = 250)
	private String description;
	
	@NotNull
	private Long duration;
	
	@NotNull
	private int rating;
	
	@NotNull
	@Column(nullable = true, length = 64)
	private String image;
	
//	@NotNull
//	private Long releaseYear;
	
	// relation
	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "movie")
	
	private Set<MovieCategory> moviecategories = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
//	private Long id;
//	private String name;
//	private String type;
//	private Long duration;
//	private Long releaseYear;
	
	
}
