package com.discs.genres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.discs.genres.ErrorCodes;
import com.discs.genres.dao.GenreRepository;
import com.discs.genres.dao.SequenceDao;
import com.discs.genres.model.Genre;
import com.discs.genres.model.response.GenresGeneralResponse;

@CrossOrigin
@RestController
public class GenresController extends ErrorCodes {
	  
	private static final String GENRES_SEQ_KEY = "genre";
	
	@Autowired
	Environment environment;
	@Autowired
	GenreRepository genreRepository;
	@Autowired
	SequenceDao sequenceDao;
	
	@GetMapping("/genres/test")
	public String test(){
	    String resp = "The Genres Service is works on the port: " + environment.getProperty("local.server.port");
	    return resp;
	}
	
	@RequestMapping(value = "/genres/list")
	public GenresGeneralResponse list(@RequestParam String order){
		List<Genre> genreList = null;
		
		if (order != null && !order.equals("") && order.toUpperCase().equals("ASC")) {
			genreList = genreRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		}else if (order != null && !order.equals("") && order.toUpperCase().equals("DESC")) {
			genreList = genreRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
		}
		
		GenresGeneralResponse genresGeneralResponse = new GenresGeneralResponse();
		genresGeneralResponse.setErrorCode(0);
		genresGeneralResponse.setErrorMsg("OK");
		genresGeneralResponse.setGenreList(genreList);
		genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return genresGeneralResponse;
	}
	
	@RequestMapping(value = "/genres/find")
	public GenresGeneralResponse findById(@RequestParam String id){
		
		GenresGeneralResponse genresGeneralResponse = new GenresGeneralResponse();
		
		Genre genre = genreRepository.findById(Long.parseLong(id));
		
		if (genre == null) {
			genresGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			genresGeneralResponse.setErrorMsg("Error: Genre not found");
			genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		} else {
			genresGeneralResponse.setErrorCode(0);
			genresGeneralResponse.setErrorMsg("OK");
			genresGeneralResponse.setGenre(genre);
			genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
	    return genresGeneralResponse;
	}
	
	@RequestMapping(value = "/genres/add", method = RequestMethod.POST)
	public GenresGeneralResponse add(@RequestBody(required=true) Genre genre){
		genre.setIdGenre(sequenceDao.getNextSequenceId(GENRES_SEQ_KEY));
		genreRepository.save(genre);
		
		GenresGeneralResponse genresGeneralResponse = new GenresGeneralResponse();
		genresGeneralResponse.setErrorCode(0);
		genresGeneralResponse.setErrorMsg("OK");
		genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return genresGeneralResponse;
	}
	
	@RequestMapping(value = "/genres/edit", method = RequestMethod.POST)
	public GenresGeneralResponse edit(@RequestBody(required=true) Genre genre){
		
		GenresGeneralResponse genresGeneralResponse = new GenresGeneralResponse();
		
		if (genre.getIdGenre() == 0) {
			genresGeneralResponse.setErrorCode(REQUIRED_FIELD);
			genresGeneralResponse.setErrorMsg("Error: the field genreId is required");
			genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			GenresGeneralResponse ggrTemp = findById(String.valueOf(genre.getIdGenre()));
			if (ggrTemp.getErrorCode() == RECORD_NOT_FOUND){
				genresGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
				genresGeneralResponse.setErrorMsg("Error: Genre not found");
				genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
			}else {
				genresGeneralResponse.setErrorCode(0);
				genresGeneralResponse.setErrorMsg("OK");
				genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
				genreRepository.save(genre);
			}
		}
		return genresGeneralResponse;
	}
	
	@RequestMapping(value = "/genres/delete")
	public GenresGeneralResponse delete(@RequestParam String id){
		
		GenresGeneralResponse genresGeneralResponse = new GenresGeneralResponse();
		
		Genre genre = genreRepository.findById(Long.parseLong(id));
		
		if (genre == null){
			genresGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			genresGeneralResponse.setErrorMsg("Error: Genre not found");
			genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			genreRepository.delete(genre);
			genresGeneralResponse.setErrorCode(0);
			genresGeneralResponse.setErrorMsg("OK");
			genresGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		
		return genresGeneralResponse;
	}
	
}
