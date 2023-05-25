package com.caixabanktech.arq.tweetsservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caixabanktech.arq.tweetsservice.domain.TweetDomain;
import com.caixabanktech.arq.tweetsservice.service.TweetService;


@RestController
@RequestMapping("/tweets")
public class TweetsController {

    private final TweetService tweetService;

    public TweetsController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public ResponseEntity<List<TweetDomain>> getTweets(@RequestParam(name="page") Optional<Integer> page, @RequestParam("elementsPerPage") Optional<Integer> elementsPerPage){

    	int pageValue=0;
    	int elementsPerPageValue=10;
    	if (page.isPresent()) {
    		pageValue=page.get();
    	}
    	if (elementsPerPage.isPresent()) {
    		elementsPerPageValue=elementsPerPage.get();
    	}
        List<TweetDomain> resultPage = tweetService.getTweets(pageValue, elementsPerPageValue);
        ResponseEntity<List<TweetDomain>> tweetDomainResponseEntity = new ResponseEntity<List<TweetDomain>>(resultPage, null, HttpStatus.OK);
        return tweetDomainResponseEntity;
    }

    @GetMapping("/{tweetId}")
    public ResponseEntity<TweetDomain> getTweet(@PathVariable String tweetId){
    	TweetDomain tweet=tweetService.getTweet(UUID.fromString(tweetId));
    	if (tweet==null) {
    		return ResponseEntity.notFound().build();
    	}else {
    		return ResponseEntity.ok(tweet);
    	}        
    }

    @PostMapping
    public ResponseEntity<TweetDomain> addTweet(@RequestBody TweetDomain tweet){
        TweetDomain tweetDomain = tweetService.createTweet(tweet);
        ResponseEntity<TweetDomain> tweetDomainResponseEntity = new ResponseEntity<TweetDomain>(tweetDomain, null, HttpStatus.CREATED);
        return tweetDomainResponseEntity;
    }

    @PutMapping("{tweetId}")
    public ResponseEntity<TweetDomain> updateTweet(@PathVariable String tweetId,@RequestBody TweetDomain tweet){
    	tweet.setId(UUID.fromString(tweetId));
    	TweetDomain tweetDomain = tweetService.updateTweet(tweet);
        return ResponseEntity.status(HttpStatus.CREATED).body(tweetDomain);
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<?> deleteTweet(@PathVariable String tweetId){
        try {
            tweetService.delete(UUID.fromString(tweetId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
