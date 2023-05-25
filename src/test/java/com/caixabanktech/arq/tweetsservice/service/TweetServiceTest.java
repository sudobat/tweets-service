package com.caixabanktech.arq.tweetsservice.service;

import java.util.Date;
import java.util.UUID;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
import com.caixabanktech.arq.tweetsservice.domain.repository.TweetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TweetServiceTest {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepo;	
	
	@Test
	void givenExistentElementDeleteFromDatabaseThenValidate() {
		UUID uuid = UUID.randomUUID();
		Tweet tweet=new Tweet(uuid,"Tweet","Pepe",new Date());
		Tweet tweet1=tweetRepo.save(tweet);
		assertEquals(tweet1,tweet1);
		assertEquals(tweetRepo.count(),1);
		tweetService.delete(uuid);
		
		assertEquals(tweetRepo.count(),0);
	}
	
	@Test
	void givenNonExistentElementDontDeleteFromDatabase() {
		UUID uuid = UUID.randomUUID();
		tweetService.delete(uuid);
	}

	@Test
	void givenATweetCreateElementInDataBase() {
		TweetDomain tweet = new TweetDomain("Tweet", "Pepe", new Date(), null);
		String uuid = tweetService.createTweet(tweet);
		assertNotNull(uuid);
		assertEquals(tweetRepo.count(),1);
	}

	@Test
	void givenFirstPageGetTweetsOrderedDesc() {
		TweetDomain tweet = new TweetDomain("Tweet", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet2", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet3", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet4", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet5", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		assertEquals(tweetRepo.count(),5);
		assertEquals(tweetService.getTweets(0).get(0).getDesc(), "Tweet5");
	}

	@Test
	void givenAnotherPageGetTweetsOrderedDesc() {
		TweetDomain tweet = new TweetDomain("Tweet", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet2", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet3", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet4", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet5", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet6", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet7", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet8", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet9", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet10", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet11", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		tweet = new TweetDomain("Tweet12", "Pepe", new Date(), null);
		tweetService.createTweet(tweet);
		assertEquals(tweetRepo.count(),12);
		assertEquals(tweetService.getTweets(1).get(0).getDesc(), "Tweet2");
	}


}
