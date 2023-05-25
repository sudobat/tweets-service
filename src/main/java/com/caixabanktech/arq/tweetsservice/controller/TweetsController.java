package com.caixabanktech.arq.tweetsservice.controller;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
import com.caixabanktech.arq.tweetsservice.service.TweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tweets")
public class TweetsController {

    private final TweetService tweetService;

    public TweetsController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public ResponseEntity<List<Tweet>> getTweets(){
        return null;
    }

    @GetMapping("{tweetId}")
    public ResponseEntity<Tweet> getTweet(UUID uuid){
        return null;
    }

    @PostMapping
    public ResponseEntity<Tweet> addTweet(Tweet tweet){
        return null;
    }

    @PutMapping("{tweetId}")
    public ResponseEntity<Tweet> updateTweet(Tweet tweet){
        return null;
    }

    @DeleteMapping("{tweetId}")
    public void deleteTweet(UUID id){
        tweetService.delete(id);
    }
}
