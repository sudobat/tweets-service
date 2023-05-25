package com.caixabanktech.arq.tweetsservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.caixabanktech.arq.tweetsservice.domain.mapper.TweetMapper;
import com.caixabanktech.arq.tweetsservice.domain.repository.TweetRepository;

@Service
public class TweetService {

	private TweetRepository repo;
	private TweetMapper mapper;
	
	public TweetService(TweetRepository repo,TweetMapper mapper) {
		this.repo=repo;
		this.mapper=mapper;
	}
	
	
	/**
	 * Deletting the tweet from the BBDD
	 * @param id
	 */
	public void delete(UUID id) {
		repo.deleteById(id);
	}
}



