package com.caixabanktech.arq.tweetsservice.controller;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;
import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;
import com.caixabanktech.arq.tweetsservice.service.TweetService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<TweetDomain>> getTweets(@RequestParam("page") int page){

        List<TweetDomain> resultPage = tweetService.getTweets(page);
        ResponseEntity<List<TweetDomain>> tweetDomainResponseEntity = new ResponseEntity<List<TweetDomain>>(resultPage, null, HttpStatus.CREATED);
        return tweetDomainResponseEntity;
    }

    @GetMapping("{tweetId}")
    public ResponseEntity<Tweet> getTweet(UUID uuid){

        return null;
    }

    @PostMapping
    public ResponseEntity<TweetDomain> addTweet(TweetDomain tweet){
        TweetDomain tweetDomain = tweetService.createTweet(tweet);
        ResponseEntity<TweetDomain> tweetDomainResponseEntity = new ResponseEntity<TweetDomain>(tweetDomain, null, HttpStatus.CREATED);
        return tweetDomainResponseEntity;
    }

    @PutMapping("{tweetId}")
    public ResponseEntity<Tweet> updateTweet(Tweet tweet){
        return null;
    }

    @DeleteMapping("{tweetId}")
    public ResponseEntity<?> deleteTweet(UUID id){
        try {
            tweetService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
