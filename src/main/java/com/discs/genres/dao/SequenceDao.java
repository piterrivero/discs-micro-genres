package com.discs.genres.dao;

import org.springframework.stereotype.Repository;

import com.discs.genres.exceptions.SequenceException;

@Repository
public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;
	
}
