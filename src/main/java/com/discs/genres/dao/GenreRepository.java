package com.discs.genres.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discs.genres.model.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, Long>, GenreRepositoryCustom {
	
	List<Genre> findAll(Sort sortByNameAtAsc);
	
	Genre findById(long id);

		
}
