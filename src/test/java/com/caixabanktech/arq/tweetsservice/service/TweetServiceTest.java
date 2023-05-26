package com.caixabanktech.arq.tweetsservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;
import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
import com.caixabanktech.arq.tweetsservice.domain.repository.TweetRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TweetServiceTest {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepo;	
    
    @Test
    void givenValidUUIDUpdateTweet() {
		TweetDomain tweet=new TweetDomain("Tweet","PepeUpdate",new Date(),null);	
		//Create
		tweet=tweetService.createTweet(tweet);
		tweet.setDesc("PepeOtroUpdate");
		//Update
		tweetService.updateTweet(tweet);
		
		TweetDomain tweet2=tweetService.getTweet(tweet.getId());
		assertEquals("PepeOtroUpdate",tweet2.getDesc());
		
    }
	
	@Test
	void givenExistentElementDeleteFromDatabaseThenValidate() {
		UUID uuid = UUID.randomUUID();
		Tweet tweet=new Tweet(uuid,"Tweet","Pepe",new Date());
		Tweet tweet1=tweetRepo.save(tweet);
		assertEquals(tweet1,tweet1);
		assertEquals(1,tweetRepo.count());
		tweetService.delete(uuid);		
		assertEquals(0,tweetRepo.count());
	}

	@Test
	void giveninValidUUIDThenReturnTweet() {
		UUID uuid = UUID.randomUUID();
		UUID uuid1 = UUID.randomUUID();		
		TweetDomain tweet=new TweetDomain("Tweet","Pepe1",new Date(),uuid);		
		tweetService.createTweet(tweet);		
		assertNull(tweetService.getTweet(uuid1));
	}
	
	@Test
	void givenValidUUIDThenReturnTweet() {
		TweetDomain tweet=new TweetDomain("Tweet","Pepe1",new Date(),null);		
		tweet=tweetService.createTweet(tweet);		
		assertNotNull(tweetService.getTweet(tweet.getId()));
	}
	
	@Test
	void givenNonExistentElementDontDeleteFromDatabase() {
		UUID uuid = UUID.randomUUID();
		tweetService.delete(uuid);
	}

	@Test
	void givenATweetCreateElementInDataBase() {
		TweetDomain tweet = new TweetDomain("Tweet", "Pepe", new Date(), null);
		TweetDomain tweetResult = tweetService.createTweet(tweet);
		assertNotNull(tweetResult.getId());
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
		assertEquals(tweetService.getTweets(0,5).get(0).getDesc(), "Tweet5");
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
		assertEquals(12, tweetRepo.count());
		assertEquals( "Tweet12",tweetService.getTweets(0,12).get(0).getDesc());
		assertEquals( "Tweet",tweetService.getTweets(0,12).get(11).getDesc());
	}


}
