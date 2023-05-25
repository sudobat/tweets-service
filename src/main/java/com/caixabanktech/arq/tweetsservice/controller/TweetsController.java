package com.caixabanktech.arq.tweetsservice.controller;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
import com.caixabanktech.arq.tweetsservice.domain.service.TweetService;
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
    public List<Tweet> getTweets(){
        return null;
    }

    @GetMapping("{tweetId}")
    public Tweet getTweet(UUID uuid){
        return null;
    }

    @PostMapping
    public Tweet addTweet(Tweet tweet){
        return null;
    }

    @PutMapping("{tweetId}")
    public Tweet updateTweet(Tweet tweet){
        return null;
    }

    @DeleteMapping("{tweetId}")
    public void deleteTweet(UUID id){

    }
}
