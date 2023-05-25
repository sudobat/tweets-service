package com.caixabanktech.arq.tweetsservice.service;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;
import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
import com.caixabanktech.arq.tweetsservice.domain.mapper.TweetMapper;
import com.caixabanktech.arq.tweetsservice.domain.repository.TweetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TweetService {

	private TweetRepository repo;
	private TweetMapper mapper;
	
	public TweetService(TweetRepository repo,TweetMapper mapper) {
		this.repo=repo;
		this.mapper=mapper;
	}

	public List<TweetDomain> getTweets(int page) {
		Pageable pageable = PageRequest.of(page, 10);
        List<Tweet> tweets = repo.findTweets(pageable);
		return mapper.mapListToDomain(tweets);
	}

	public String createTweet(TweetDomain tweet) {
		tweet.setId(UUID.randomUUID());
		Tweet tweetResult = repo.save(mapper.map(tweet));
		return mapper.map(tweetResult).getId().toString();
	}
	
	/**
	 * Deletting the tweet from the BBDD
	 * @param id
	 */
	public void delete(UUID id) {
		repo.deleteById(id);
	}
}



