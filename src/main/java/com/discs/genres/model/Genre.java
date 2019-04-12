package com.discs.genres.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genre")
public class Genre {

	@Id
	private long idGenre;
	
	@Indexed(unique = true)
	private String genre;
	
	public Genre() {
	}

	public long getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(long idGenre) {
		this.idGenre = idGenre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
