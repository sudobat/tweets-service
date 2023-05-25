package com.caixabanktech.arq.tweetsservice.service;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
import com.caixabanktech.arq.tweetsservice.domain.repository.TweetRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
