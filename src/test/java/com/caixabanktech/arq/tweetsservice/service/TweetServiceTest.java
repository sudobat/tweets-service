package com.caixabanktech.arq.tweetsservice.service;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TweetServiceTest {

    @Autowired
    private TweetService tweetService;

	
	
	@Test
	void givenExistentElementDeleteFromDatabase() {
		UUID uuid = UUID.randomUUID();
	
	}
	
	
	@Test
	void givenNonExistentElementDontDeleteFromDatabase() {
		UUID uuid = UUID.randomUUID();
		tweetService.delete(uuid);
	}
}
