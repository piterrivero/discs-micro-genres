package com.discs.genres.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {

	@Autowired
    MongoTemplate mongoTemplate;

}
