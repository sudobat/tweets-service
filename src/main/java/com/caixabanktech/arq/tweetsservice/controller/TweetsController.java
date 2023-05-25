package com.caixabanktech.arq.tweetsservice.controller;

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
    public ResponseEntity<List<Tweet>> getTweets(@RequestParam("page") int page){

        //Page<Tweet> resultPage = tweetService.getTweets(page, 10);
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
    public ResponseEntity<?> deleteTweet(UUID id){
        try {
            tweetService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
