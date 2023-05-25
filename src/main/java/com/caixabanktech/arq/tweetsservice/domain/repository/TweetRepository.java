package com.caixabanktech.arq.tweetsservice.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caixabanktech.arq.tweetsservice.domain.entity.Tweet;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,UUID>{
    @Query("select t from Tweet t order by t.createdAt DESC")
    List<Tweet> findTweets(Pageable pageable);

}
