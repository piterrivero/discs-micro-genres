package com.discs.genres.model.response;

import java.util.List;

import com.discs.genres.model.Genre;

public class GenresGeneralResponse {

	private Genre genre;
	private List<Genre> genreList;
	private int errorCode;
	private String errorMsg;
	private String responsePort;
	
	public GenresGeneralResponse() {
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public List<Genre> getGenreList() {
		return genreList;
	}
	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getResponsePort() {
		return responsePort;
	}
	public void setResponsePort(String responsePort) {
		this.responsePort = responsePort;
	}
	
}
