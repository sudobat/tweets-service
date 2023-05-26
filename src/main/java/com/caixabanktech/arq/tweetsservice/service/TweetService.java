package com.caixabanktech.arq.tweetsservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;
import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
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

	public TweetDomain getTweet(UUID id) {
		
		Optional<Tweet> opt=repo.findById(id);
		if (opt.isPresent()) {
			return mapper.map(opt.get());
		}else {
			return null;
		}
	}
	
	public List<TweetDomain> getTweets(int page, int numbePerPage) {
		Pageable pageable = PageRequest.of(page, numbePerPage, Sort.by("createdAt").descending());
        Page<Tweet> tweets = repo.findAll(pageable);
		return mapper.mapListToDomain(tweets.toList());
	}

	public TweetDomain createTweet(TweetDomain tweet) {
		if (tweet.getId()==null) {
			tweet.setId(UUID.randomUUID());
		}
		tweet.setCreatedAt(new Date());//Timestamp actual
		Tweet tweetResult = repo.save(mapper.map(tweet));
		return mapper.map(tweetResult);
	}
	
	public TweetDomain updateTweet(TweetDomain tweet) {
		
		Optional<Tweet> opt=repo.findById(tweet.getId());
		if (!opt.isPresent()) {
			return null;
		}else {
			Tweet tweetResult=opt.get();
			tweetResult.setDesc(tweet.getDesc());
			return mapper.map(repo.save(mapper.map(tweet)));
		}
	}
	
	/**
	 * Deletting the tweet from the BBDD
	 * @param id
	 */
	public void delete(UUID id) {
		repo.deleteById(id);
	}
}



